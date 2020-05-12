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
    /**
     * Fetch a list of available source accounts.
     */
    suspend fun getSourceAccounts(): List<Account>

    /**
     * Initiates a new transfer, and then starts observing for updates.
     *
     * The [SignableOperation] obtained through the [StreamObserver.onNext] function will let you
     * listen to updates until you have reached one of the [endstates][SignableOperation.Status],
     * or until an error is observed through [StreamObserver.onError].
     *
     * @param descriptor Information about the transfer that should be created
     * @param streamObserver The [StreamObserver] that observes updates to the transfer.
     * These updates will be delivered in [SignableOperation] format,
     * from which you can read the [status][SignableOperation.Status] of the operation.
     *
     * @return A [StreamSubscription] that allows you to stop receiving updates to the
     * [streamObserver] by calling [StreamSubscription.unsubscribe].
     */
    fun initiateTransfer(
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

    /**
     * Creates a new transfer, returning a [SignableOperation] object.
     */
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

    override fun initiateTransfer(
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

        val streamObserverWrapper = object : StreamObserver<SignableOperation> {

            override fun onNext(value: SignableOperation) {
                streamObserver.onNext(value)
                if (value.status.isEndState()) {
                    pollingSubscription?.unsubscribe()
                    onCompleted()
                }
            }

            override fun onCompleted() = streamObserver.onCompleted()
            override fun onError(error: Throwable) = streamObserver.onError(error)
        }

        CoroutineScope(serviceDispatcher + creationJob + exceptionHandler).launch {

            val firstOperation = createTransfer(descriptor)

            yield()

            streamObserver.onNext(firstOperation)

            if (!firstOperation.status.isEndState()) {
                pollingSubscription =
                    streamSignableOperation(firstOperation.underlyingId).subscribe(streamObserverWrapper)
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
