package com.tink.model.transfer

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import com.tink.model.account.Account
import kotlinx.android.parcel.IgnoredOnParcel

/**
 * A beneficiary of a transfer
 *
 * @param accountId The id of the source account this beneficiary belongs to, see [Account.id]
 * @param accountNumber The account number of the beneficiary, for example the BG/PG number or the IBAN
 * @param name The name of the beneficiary
 * @param type The type of transfer that is used for this beneficiary, for example "iban"
 */
@Parcelize
data class Beneficiary(
    val accountId: String,
    val accountNumber: String,
    val name: String,
    val type: String
) : Parcelable {

    /*
     * The uri which can be used to make a transfer to this beneficiary
     */
    @IgnoredOnParcel
    val uri: String = "$type://$accountNumber"
}
