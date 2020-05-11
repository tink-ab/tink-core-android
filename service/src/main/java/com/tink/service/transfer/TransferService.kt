package com.tink.service.transfer

import com.tink.model.account.Account
import com.tink.model.transfer.SignableOperation
import com.tink.rest.apis.TransferApi
import com.tink.rest.models.Transfer
import com.tink.service.account.toCoreModel
import com.tink.service.streaming.PollingHandler
import com.tink.service.streaming.publisher.Stream
import java.lang.Exception
import javax.inject.Inject

interface TransferService {
    suspend fun createTransfer(descriptor: CreateTransferDescriptor): SignableOperation
    suspend fun getSourceAccounts(): List<Account>
    fun getSignableOperation(transferId: String): Stream<SignableOperation> // TODO: Endpoint naming
}

class TransferServiceImpl @Inject constructor(
    private val transferApi: TransferApi
) : TransferService {

    override suspend fun createTransfer(descriptor: CreateTransferDescriptor) =
        transferApi.createTransfer(
            Transfer(
                credentialsId = descriptor.credentialsId,
                amount = descriptor.amount.value.toBigDecimal().toDouble(),
                currency = descriptor.amount.currencyCode,
                destinationMessage = descriptor.destinationMessage,
                destinationUri = descriptor.destinationUri,
                sourceMessage = descriptor.sourceMessage,
                sourceUri = descriptor.sourceUri
            )
        ).toCoreModel()

    override suspend fun getSourceAccounts() =
        transferApi.getSourceAccounts().accounts?.map { it.toCoreModel() } ?: emptyList()

    override fun getSignableOperation(transferId: String): Stream<SignableOperation> {
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
