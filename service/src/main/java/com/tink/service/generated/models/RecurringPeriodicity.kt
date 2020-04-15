/**
 * NOTE: This class is auto generated by the Swagger Gradle Codegen for the following API: Tink API
 *
 * More info on this tool is available on https://github.com/Yelp/swagger-gradle-codegen
 */

package com.tink.service.generated.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @property periodUnit Recurring periodicity unit.
 */
@JsonClass(generateAdapter = true)
data class RecurringPeriodicity(
    @Json(name = "periodUnit") @field:Json(name = "periodUnit") var periodUnit: RecurringPeriodicity.PeriodUnitEnum
) {
    /**
     * Recurring periodicity unit.
     * Values: WEEK, MONTH, YEAR
     */
    @JsonClass(generateAdapter = false)
    enum class PeriodUnitEnum(val value: String) {
        @Json(name = "WEEK") WEEK("WEEK"),
        @Json(name = "MONTH") MONTH("MONTH"),
        @Json(name = "YEAR") YEAR("YEAR")
    }
}
