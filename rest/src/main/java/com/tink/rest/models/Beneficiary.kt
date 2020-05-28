package com.tink.rest.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * A beneficiary is a payment or transfer destination account which has been authorized by the bank.
 * Each beneficiary belongs to an account, which means that the given account can send money to that beneficiary.
 *
 * However, different banks treat beneficiaries in different ways.
 * Some treat them as fully trusted, meaning no signing at all is required when transferring money to the beneficiary.
 * Other banks treat them more as address books of registered recipients.
 *
 * @property ownerAccountId The internal identifier of the account that this beneficiary belongs to.
 * @property name The name chosen by the user for this beneficiary.
 * @property accountNumberType The type of the [accountNumber] that this beneficiary has. See [AccountNumberTypes] for supported types.
 * @property accountNumber The account number for the beneficiary. The structure of this field depends on the [accountNumberType].
 */
@JsonClass(generateAdapter = true)
data class Beneficiary(
    @Json(name = "ownerAccountId") val ownerAccountId: String,
    @Json(name = "name") val name: String,
    @Json(name = "accountNumberType") val accountNumberType: String,
    @Json(name = "accountNumber") val accountNumber: String
) {
//    /**
//     * The type of the `accountNumber` that this beneficiary has.
//     * Values: SE, SE_BG, SE_PG, IBAN, SORT_CODE
//     */
//    @JsonClass(generateAdapter = false)
//    enum class AccountNumberType(val value: String) {
//        @Json(name = "se") SE("se"),
//        @Json(name = "se-bg") SE_BG("se-bg"),
//        @Json(name = "se-pg") SE_PG("se-pg"),
//        @Json(name = "iban") IBAN("iban"),
//        @Json(name = "sort-code") SORT_CODE("sort-code")
//    }

    /**
     * The type of the [accountNumber] that this beneficiary has.
     * Values: SE, SE_BG, SE_PG, IBAN, SORT_CODE
     */
    object AccountNumberTypes {
        const val SE = "se"
        const val SE_BG = "se-bg"
        const val SE_PG = "se-pg"
        const val IBAN = "iban"
        const val SORT_CODE = "sort-code"
    }
}
