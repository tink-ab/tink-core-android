/**
 * NOTE: This class is auto generated by the Swagger Gradle Codegen for the following API: Sample Pet Store App
 *
 * More info on this tool is available on https://github.com/Yelp/swagger-gradle-codegen
 */

package com.tink.service.generated.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @property description Identifier of the data the statistic represents. This could for example be a category ID.
 * @property periods Time periods for the statistics: year, month, week or day. Format: &#x60;2014&#x60;, &#x60;2014-02&#x60;, &#x60;2014:45&#x60; or &#x60;2014-02-12&#x60;.
 * @property resolution Resolution for the statistics. Note that monthly statistics will be calculated only with the resolution that the user has in the user settings (MONTHLY, MONTHLY_ADJUSTED), and not for both.
 * @property types A list of types of statistics. Default: &#x60;expenses-by-category&#x60;, &#x60;income-by-category&#x60;, &#x60;left-to-spend&#x60;, &#x60;left-to-spend-average&#x60;. Read more about statistics for type information.
 * @property padResultUntilToday Indicates if the result should be flat filled until the period of today.
 */
@JsonClass(generateAdapter = true)
data class StatisticQuery(
    @Json(name = "description") @field:Json(name = "description") var description: String? = null,
    @Json(name = "periods") @field:Json(name = "periods") var periods: List<String>? = null,
    @Json(name = "resolution") @field:Json(name = "resolution") var resolution: StatisticQuery.ResolutionEnum? = null,
    @Json(name = "types") @field:Json(name = "types") var types: List<String>? = null,
    @Json(name = "padResultUntilToday") @field:Json(name = "padResultUntilToday") var padResultUntilToday: Boolean? = null
) {
    /**
     * Resolution for the statistics. Note that monthly statistics will be calculated only with the resolution that the user has in the user settings (MONTHLY, MONTHLY_ADJUSTED), and not for both.
     * Values: DAILY, MONTHLY, MONTHLY_ADJUSTED, YEARLY, ALL, WEEKLY
     */
    @JsonClass(generateAdapter = false)
    enum class ResolutionEnum(val value: String) {
        @Json(name = "DAILY") DAILY("DAILY"),
        @Json(name = "MONTHLY") MONTHLY("MONTHLY"),
        @Json(name = "MONTHLY_ADJUSTED") MONTHLY_ADJUSTED("MONTHLY_ADJUSTED"),
        @Json(name = "YEARLY") YEARLY("YEARLY"),
        @Json(name = "ALL") ALL("ALL"),
        @Json(name = "WEEKLY") WEEKLY("WEEKLY")
    }
}
