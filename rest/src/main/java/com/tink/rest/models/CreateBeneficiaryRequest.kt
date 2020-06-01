/**
 * NOTE: This class is auto generated by the Swagger Gradle Codegen for the following API: Tink API
 *
 * More info on this tool is available on https://github.com/Yelp/swagger-gradle-codegen
 */

package com.tink.rest.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @property ownerAccountId The identifier of the source account that this beneficiary should be added to.
 * @property credentialsId The ID of the &#x60;Credentials&#x60; used to add the beneficiary. Note that you can send in a different ID here than the credentials ID to which the account belongs. This functionality exists to support the case where you may have double credentials for one financial institution, due to PSD2 regulations.
 * @property name The name chosen by the user for this beneficiary.
 * @property accountNumberType The type of the &#x60;accountNumber&#x60; that this beneficiary has.
 * @property accountNumber The account number for the beneficiary. The structure of this field depends on the &#x60;accountNumberType&#x60;.
 */
@JsonClass(generateAdapter = true)
data class CreateBeneficiaryRequest(
    @Json(name = "ownerAccountId") @field:Json(name = "ownerAccountId") var ownerAccountId: String,
    @Json(name = "credentialsId") @field:Json(name = "credentialsId") var credentialsId: String,
    @Json(name = "name") @field:Json(name = "name") var name: String,
    @Json(name = "accountNumberType") @field:Json(name = "accountNumberType") var accountNumberType: String,
    @Json(name = "accountNumber") @field:Json(name = "accountNumber") var accountNumber: String
)
