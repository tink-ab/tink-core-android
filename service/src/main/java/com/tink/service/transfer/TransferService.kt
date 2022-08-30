package com.tink.service.transfer

import com.tink.model.account.Account
import com.tink.model.transfer.Beneficiary
import com.tink.model.transfer.SignableOperation
import com.tink.rest.apis.BeneficiaryApi
import com.tink.rest.apis.TransferApi
import com.tink.rest.models.CreateTransferRequest
import com.tink.rest.tools.unwrap
import com.tink.service.account.toCoreModel
import com.tink.service.network.TinkConfiguration
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

    /**
     * Lists all beneficiaries of the current user
     */
    suspend fun getBeneficiaries(): List<Beneficiary>

    /**
     * Add a new beneficiary
     */
    suspend fun addBeneficiary(descriptor: CreateBeneficiaryDescriptor)
}

internal class TransferServiceImpl @Inject constructor(
    private val transferApi: TransferApi,
    private val beneficiaryApi: BeneficiaryApi,
    private val configuration: TinkConfiguration
) : TransferService {

    override suspend fun getSourceAccounts() =
        transferApi.getSourceAccounts().accounts?.map { it.toCoreModel() } ?: emptyList()

    /**
     * Creates a new transfer, returning a [SignableOperation] object.
     */
    override suspend fun initiateTransfer(descriptor: CreateTransferDescriptor) =
        transferApi.createTransfer(
            CreateTransferRequest(
                amount = descriptor.amount.value.toBigDecimal().toDouble(),
                currency = descriptor.amount.currencyCode,
                destinationMessage = descriptor.destinationMessage,
                destinationUri = descriptor.beneficiaryUri,
                sourceMessage = descriptor.sourceMessage,
                sourceUri = descriptor.sourceAccountUri,
                credentialsId = descriptor.credentialsId,
                redirectUri = configuration.redirectUri.toString()
            )
        ).toCoreModel()

    override suspend fun getTransferStatus(transferId: String): SignableOperation =
        transferApi.getSignableOperation(transferId).toCoreModel()

    override suspend fun getBeneficiaries(): List<Beneficiary> =
        beneficiaryApi.list().beneficiaries.map { it.toCoreModel() }

    override suspend fun addBeneficiary(descriptor: CreateBeneficiaryDescriptor) =
        beneficiaryApi.create(descriptor.toRequest()).unwrap()
}
