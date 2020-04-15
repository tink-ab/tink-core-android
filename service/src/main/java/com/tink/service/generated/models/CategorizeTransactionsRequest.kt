/**
 * NOTE: This class is auto generated by the Swagger Gradle Codegen for the following API: Tink API
 *
 * More info on this tool is available on https://github.com/Yelp/swagger-gradle-codegen
 */

package com.tink.service.generated.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @property categoryId The internal identifier of the category that the list of transactions is categorized to.
 * @property transactionIds A list of internal identifiers of the transactions categorized.
 */
@JsonClass(generateAdapter = true)
data class CategorizeTransactionsRequest(
    @Json(name = "categoryId") @field:Json(name = "categoryId") var categoryId: String,
    @Json(name = "transactionIds") @field:Json(name = "transactionIds") var transactionIds: List<String>
)
