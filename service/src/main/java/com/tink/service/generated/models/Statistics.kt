/**
 * NOTE: This class is auto generated by the Swagger Gradle Codegen for the following API: Sample Pet Store App
 *
 * More info on this tool is available on https://github.com/Yelp/swagger-gradle-codegen
 */

package com.tink.service.generated.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Statistics contain derived data from different types of information which is available for a user. This calculated data is stored for a certain period of time with a certain time resolution, and could be based on information such as expenses, incomes or balances. As an example, statistics could be a time series of travel expenses calculated monthly for the last six months.
 * @property description Identifier of the data the statistic represents.
 * @property payload Secondary identifier of the data the statistic represent.
 * @property period The statistic&#39;s period, depends on its resolution. One of: year, month, week or day. Format: &#x60;2014&#x60;, &#x60;2014-02&#x60;, &#x60;2014:45&#x60; or &#x60;2014-02-12&#x60;.
 * @property resolution Resolution for the statistics.
 * @property type The statistic&#39;s type.
 * @property userId The internal identifier of the user that the statistics belongs to.
 * @property value The value of the statistics for this type, period, and description.
 */
@JsonClass(generateAdapter = true)
data class Statistics(
    @Json(name = "description") @field:Json(name = "description") var description: String,
    @Json(name = "period") @field:Json(name = "period") var period: String,
    @Json(name = "resolution") @field:Json(name = "resolution") var resolution: Statistics.ResolutionEnum,
    @Json(name = "type") @field:Json(name = "type") var type: String,
    @Json(name = "userId") @field:Json(name = "userId") var userId: String,
    @Json(name = "value") @field:Json(name = "value") var value: Double,
    @Json(name = "payload") @field:Json(name = "payload") var payload: String? = null
) {
    /**
     * Resolution for the statistics.
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
