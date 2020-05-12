package com.tink.service.transfer

import com.tink.model.account.Account
import com.tink.model.transfer.SignableOperation
import com.tink.rest.apis.TransferApi
import com.tink.rest.models.Transfer
import com.tink.service.account.toCoreModel
import com.tink.service.di.SERVICE_DISPATCHER
import com.tink.service.streaming.PollingHandler
import com.tink.service.streaming.publisher.Stream
import com.tink.service.streaming.publisher.StreamObserver
import com.tink.service.streaming.publisher.StreamSubscription
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Named

interface TransferService {
    suspend fun getSourceAccounts(): List<Account>

    fun createTransfer(
        descriptor: CreateTransferDescriptor,
        streamObserver: StreamObserver<SignableOperation>
    ): StreamSubscription
}

class TransferServiceImpl @Inject constructor(
    private val transferApi: TransferApi,
    @Named(SERVICE_DISPATCHER) private val serviceDispatcher: CoroutineDispatcher
) : TransferService {

    override suspend fun getSourceAccounts() =
        transferApi.getSourceAccounts().accounts?.map { it.toCoreModel() } ?: emptyList()

    // Notes:
    // - Hot vs cold observable
    // - Hot: might miss events like the initial error
    // - Cold: will create a new transfer if accidentally subscribes again
    // Ideas:
    //     - Combine one shot and stream: Return stream in onSuccess
    //     - subscribe when creating the transfer: "createTransferAndSubscribeForUpdates(descriptor, streamObserver): StreamSubscription

    private suspend fun createTransfer(descriptor: CreateTransferDescriptor) =
        transferApi.createTransfer(
            Transfer(
                amount = descriptor.amount.value.toBigDecimal().toDouble(),
                currency = descriptor.amount.currencyCode,
                destinationMessage = descriptor.destinationMessage,
                destinationUri = descriptor.destinationUri,
                sourceMessage = descriptor.sourceMessage,
                sourceUri = descriptor.sourceUri,
                credentialsId = descriptor.credentialsId
            )
        ).toCoreModel()


    // TODO: docs
    override fun createTransfer(
        descriptor: CreateTransferDescriptor,
        streamObserver: StreamObserver<SignableOperation>
    ): StreamSubscription {

        var pollingSubscription: StreamSubscription? = null

        val creationJob = Job()

        val subscription = object : StreamSubscription {
            override fun unsubscribe() {
                creationJob.cancel()
                pollingSubscription?.unsubscribe() ?: streamObserver.onCompleted()
            }
        }

        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            streamObserver.onError(throwable)
            pollingSubscription?.unsubscribe()
        }

        CoroutineScope(serviceDispatcher + creationJob + exceptionHandler).launch {

            val firstOperation = createTransfer(descriptor)

            yield()

            streamObserver.onNext(firstOperation)

            if (firstOperation.status != SignableOperation.Status.FAILED) {
                pollingSubscription =
                    streamSignableOperation(firstOperation.underlyingId).subscribe(streamObserver)
            } else {
                streamObserver.onCompleted()
            }
        }

        return subscription
    }

    private fun streamSignableOperation(transferId: String): Stream<SignableOperation> {
        return PollingHandler { observer ->
            try {
                val result = transferApi.getSignableOperation(transferId).toCoreModel()
                observer.onNext(result)
            } catch (exception: Exception) {
                observer.onError(exception)
            }
        }
    }
}
