package com.tink.service.transfer

import com.tink.model.account.Account
import com.tink.rest.models.Credentials


/**
 * Descriptor to create a beneficiary of a transfer
 *
 * @param ownerAccountId The id of the source account the beneficiary belongs to, see [Account.id]
 * @param credentialsId The id of the [Credentials] used to add the beneficiary. Note that you can send in a different id here than the credentials id to which the account belongs. This functionality exists to support the case where you may have double credentials for one financial institution, due to PSD2 regulations.
 * @param accountNumber The account number of the beneficiary, for example the BG/PG number or the IBAN
 * @param name The name of the beneficiary
 * @param accountNumberType The type of transfer that is used for the beneficiary, for example "iban"
 */
data class CreateBeneficiaryDescriptor(
    val ownerAccountId: String,
    val credentialsId: String,
    val accountNumber: String,
    val accountNumberType: String,
    val name: String
)
