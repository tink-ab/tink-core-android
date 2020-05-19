package com.tink.service.transfer

import com.tink.model.account.Account
import com.tink.model.transfer.Beneficiary
import com.tink.model.transfer.SignableOperation
import com.tink.rest.apis.TransferApi
import com.tink.rest.models.Transfer
import com.tink.service.account.toCoreModel
import javax.inject.Inject

interface TransferService {
    /**
     * Fetch a list of available source accounts.
     */
    suspend fun getSourceAccounts(): List<Account>

    /**
     * Initiates a new transfer
     *
     * @param descriptor Information about the transfer that should be created
     *
     * @return A [SignableOperation] from which you can read the [status][SignableOperation.Status] of the operation.
     */
    suspend fun initiateTransfer(descriptor: CreateTransferDescriptor): SignableOperation

    /**
     * Retrieves information about the current status of a transfer.
     *
     * @param transferId The id of the transfer. Note: When retrieving this from a [SignableOperation],
     * [SignableOperation.underlyingId] should be passed in here.
     *
     * @return A [SignableOperation] from which you can read the [status][SignableOperation.Status] of the operation.
     */
    suspend fun getTransferStatus(transferId: String): SignableOperation

    suspend fun getBeneficiaries(): List<Beneficiary>
}

class TransferServiceImpl @Inject constructor(
    private val transferApi: TransferApi
) : TransferService {

    override suspend fun getSourceAccounts() =
        transferApi.getSourceAccounts().accounts?.map { it.toCoreModel() } ?: emptyList()

    /**
     * Creates a new transfer, returning a [SignableOperation] object.
     */
    override suspend fun initiateTransfer(descriptor: CreateTransferDescriptor) =
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

    override suspend fun getTransferStatus(transferId: String): SignableOperation =
        transferApi.getSignableOperation(transferId).toCoreModel()

    override suspend fun getBeneficiaries(): List<Beneficiary> =
        transferApi.getBeneficiaries().beneficiaries.map { it.toCoreModel() }
}
