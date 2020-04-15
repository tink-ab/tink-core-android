/**
 * NOTE: This class is auto generated by the Swagger Gradle Codegen for the following API: Tink API
 *
 * More info on this tool is available on https://github.com/Yelp/swagger-gradle-codegen
 */

package com.tink.service.generated.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @property name The name of the Budget.
 * @property amount The target amount for the budget. The currency must match the user profile currency setting.
 * @property filter The filter defining the budget and which transactions that is included in it. The configured fields of the filter are applied as logical and operator (intersection).
 * @property oneOffPeriodicity Periodicity configuration for the one off budget.
 */
@JsonClass(generateAdapter = true)
data class CreateOneOffBudgetRequest(
    @Json(name = "name") @field:Json(name = "name") var name: String,
    @Json(name = "amount") @field:Json(name = "amount") var amount: CurrencyDenominatedAmount,
    @Json(name = "filter") @field:Json(name = "filter") var filter: Filter,
    @Json(name = "oneOffPeriodicity") @field:Json(name = "oneOffPeriodicity") var oneOffPeriodicity: OneOffPeriodicity
)
