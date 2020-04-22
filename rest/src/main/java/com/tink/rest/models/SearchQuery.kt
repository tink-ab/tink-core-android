/**
 * NOTE: This class is auto generated by the Swagger Gradle Codegen for the following API: Tink API
 *
 * More info on this tool is available on https://github.com/Yelp/swagger-gradle-codegen
 */

package com.tink.rest.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.threeten.bp.ZonedDateTime

/**
 * @property accounts A list of account IDs to filter by.
 * @property categories A list of category IDs to filter by. Could either be leaf node categories, such as the category ID corresponding to &#x60;expenses:food.restaurants&#x60;, or groups of categories, such as the category ID corresponding to &#x60;expenses:food&#x60;.
 * @property externalIds A list of external IDs to filter by.
 * @property endDate The end date of the result.
 * @property limit The limit for the result, used for paging. Defaults to 50 if not set or set to 0.
 * @property offset The offset for the result, used for paging.
 * @property order The order of the result.
 * @property queryString The string query.
 * @property sort The sort order of the result.
 * @property startDate The start date of the result.
 * @property includeUpcoming Indicates if result should include upcoming transactions.
 */
@JsonClass(generateAdapter = true)
data class SearchQuery(
    @Json(name = "accounts") @field:Json(name = "accounts") var accounts: List<String>? = null,
    @Json(name = "categories") @field:Json(name = "categories") var categories: List<String>? = null,
    @Json(name = "externalIds") @field:Json(name = "externalIds") var externalIds: List<String>? = null,
    @Json(name = "endDate") @field:Json(name = "endDate") var endDate: ZonedDateTime? = null,
    @Json(name = "limit") @field:Json(name = "limit") var limit: Int? = null,
    @Json(name = "offset") @field:Json(name = "offset") var offset: Int? = null,
    @Json(name = "order") @field:Json(name = "order") var order: SearchQuery.OrderEnum? = null,
    @Json(name = "queryString") @field:Json(name = "queryString") var queryString: String? = null,
    @Json(name = "sort") @field:Json(name = "sort") var sort: SearchQuery.SortEnum? = null,
    @Json(name = "startDate") @field:Json(name = "startDate") var startDate: ZonedDateTime? = null,
    @Json(name = "includeUpcoming") @field:Json(name = "includeUpcoming") var includeUpcoming: Boolean? = null
) {
    /**
     * The order of the result.
     * Values: ASC, DESC
     */
    @JsonClass(generateAdapter = false)
    enum class OrderEnum(val value: String) {
        @Json(name = "ASC") ASC("ASC"),
        @Json(name = "DESC") DESC("DESC")
    }
    /**
     * The sort order of the result.
     * Values: SCORE, DATE, ACCOUNT, DESCRIPTION, AMOUNT, CATEGORY
     */
    @JsonClass(generateAdapter = false)
    enum class SortEnum(val value: String) {
        @Json(name = "SCORE") SCORE("SCORE"),
        @Json(name = "DATE") DATE("DATE"),
        @Json(name = "ACCOUNT") ACCOUNT("ACCOUNT"),
        @Json(name = "DESCRIPTION") DESCRIPTION("DESCRIPTION"),
        @Json(name = "AMOUNT") AMOUNT("AMOUNT"),
        @Json(name = "CATEGORY") CATEGORY("CATEGORY")
    }
}
