/**
 * NOTE: This class is auto generated by the Swagger Gradle Codegen for the following API: Tink API
 *
 * More info on this tool is available on https://github.com/Yelp/swagger-gradle-codegen
 */

package com.tink.service.generated.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.threeten.bp.ZonedDateTime

/**
 * @property start Period start expressed as UTC epoch timestamp in milliseconds.
 * @property end Period end expressed as UTC epoch timestamp in milliseconds.
 * @property spentAmount Period spent amount.
 */
@JsonClass(generateAdapter = true)
data class BudgetPeriod(
    @Json(name = "start") @field:Json(name = "start") var start: ZonedDateTime? = null,
    @Json(name = "end") @field:Json(name = "end") var end: ZonedDateTime? = null,
    @Json(name = "spentAmount") @field:Json(name = "spentAmount") var spentAmount: CurrencyDenominatedAmount? = null
)
