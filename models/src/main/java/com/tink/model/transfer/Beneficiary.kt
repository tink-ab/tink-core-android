package com.tink.model.transfer

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import com.tink.model.account.Account
import kotlinx.android.parcel.IgnoredOnParcel

/**
 * A beneficiary is a payment or transfer destination account which has been authorized by the bank.
 * Each beneficiary belongs to an account, which means that the given account can send money to that beneficiary.
 *
 * However, different banks treat beneficiaries in different ways.
 * Some treat them as fully trusted, meaning no signing at all is required when transferring money to the beneficiary.
 * Other banks treat them more as address books of registered recipients.
 *
 * @param ownerAccountId The internal identifier of the account that this beneficiary belongs to, see [Account.id]
 * @param accountNumber The account number for the beneficiary. The structure of this field depends on the [accountNumberType].
 * @param accountNumberType The type of the [accountNumber] that this beneficiary has. See [AccountNumberTypes] for supported types.
 * @param name The name chosen by the user for this beneficiary.
 */
@Parcelize
data class Beneficiary(
    val ownerAccountId: String,
    val accountNumber: String,
    val accountNumberType: String,
    val name: String
) : Parcelable {

    /*
     * The uri which can be used to make a transfer to this beneficiary
     */
    @IgnoredOnParcel
    val uri: String = "$accountNumberType://$accountNumber"

    /**
     * The supported types for the [accountNumber] that this beneficiary has.
     */
    object AccountNumberTypes {
        const val SE = "se"
        const val SE_BG = "se-bg"
        const val SE_PG = "se-pg"
        const val IBAN = "iban"
        const val SORT_CODE = "sort-code"
    }
}
