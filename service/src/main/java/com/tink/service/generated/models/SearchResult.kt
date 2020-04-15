/**
 * NOTE: This class is auto generated by the Swagger Gradle Codegen for the following API: Tink API
 *
 * More info on this tool is available on https://github.com/Yelp/swagger-gradle-codegen
 */

package com.tink.service.generated.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @property transaction The transactions resulting from the query.
 * @property type The search type.
 */
@JsonClass(generateAdapter = true)
data class SearchResult(
    @Json(name = "type") @field:Json(name = "type") var type: SearchResult.TypeEnum,
    @Json(name = "transaction") @field:Json(name = "transaction") var transaction: TransactionResponse? = null
) {
    /**
     * The search type.
     * Values: STATEMENT, TRANSACTION, CATEGORY, BUDGET, GOAL, SUGGESTION
     */
    @JsonClass(generateAdapter = false)
    enum class TypeEnum(val value: String) {
        @Json(name = "STATEMENT") STATEMENT("STATEMENT"),
        @Json(name = "TRANSACTION") TRANSACTION("TRANSACTION"),
        @Json(name = "CATEGORY") CATEGORY("CATEGORY"),
        @Json(name = "BUDGET") BUDGET("BUDGET"),
        @Json(name = "GOAL") GOAL("GOAL"),
        @Json(name = "SUGGESTION") SUGGESTION("SUGGESTION")
    }
}
