/**
 * NOTE: This class is auto generated by the Swagger Gradle Codegen for the following API: Tink API
 *
 * More info on this tool is available on https://github.com/Yelp/swagger-gradle-codegen
 */

package com.tink.rest.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @property transaction The transaction to which the part belonged.
 * @property counterpartTransaction Counterpart transaction affected due to bilateral link being removed.
 */
@JsonClass(generateAdapter = true)
data class DeleteTransactionPartResponse(
    @Json(name = "transaction") @field:Json(name = "transaction") var transaction: Transaction,
    @Json(name = "counterpartTransaction") @field:Json(name = "counterpartTransaction") var counterpartTransaction: Transaction? = null
)
