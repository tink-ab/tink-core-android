/**
 * NOTE: This class is auto generated by the Swagger Gradle Codegen for the following API: Tink API
 *
 * More info on this tool is available on https://github.com/Yelp/swagger-gradle-codegen
 */

package com.tink.rest.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @property transaction The primary transaction.
 * @property counterpartTransaction The counterpart transaction.
 */
@JsonClass(generateAdapter = true)
data class LinkTransactionsResponse(
    @Json(name = "transaction") @field:Json(name = "transaction") var transaction: Transaction,
    @Json(name = "counterpartTransaction") @field:Json(name = "counterpartTransaction") var counterpartTransaction: Transaction
)
