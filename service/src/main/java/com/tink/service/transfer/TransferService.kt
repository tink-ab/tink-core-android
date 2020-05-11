package com.tink.service.transfer

import com.tink.model.account.Account
import com.tink.rest.apis.TransferApi
import com.tink.rest.models.Transfer
import com.tink.service.account.toCoreModel
import javax.inject.Inject

interface TransferService {
    suspend fun createTransfer(descriptor: CreateTransferDescriptor): String
    suspend fun getSourceAccounts(): List<Account>
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
        ).toString()

    override suspend fun getSourceAccounts() =
        transferApi.getSourceAccounts().accounts?.map { it.toCoreModel() } ?: emptyList()
}