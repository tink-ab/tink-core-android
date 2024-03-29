/**
 * NOTE: This class is auto generated by the Swagger Gradle Codegen for the following API: Tink API
 *
 * More info on this tool is available on https://github.com/Yelp/swagger-gradle-codegen
 */

package com.tink.rest.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * A budget represents a financial target for a defined period. The budget itself is identified by certain filter/criteria (such as accounts, categories, tags or free-text) to target expenses. Defining multiple filter properties will yield an <code>AND</code> operation, and specifying multiple values of a filter property will yield an <code>OR</code> operation.</br>Depending on the use case a budget can either be recurring (<code>WEEK</code>, <code>MONTH</code> or <code>YEAR</code>) or be seen as a one-off budget (fixed <code>start</code> and <code>end</code> time period). The amount of the budget will relate to the recurring period defined by the periodicity unit for recurring budgets, or the fixed time window for a one-off budget. A budget could for example be the goal to spend at maximum 10 euros weekly on coffee.
 * @property id The ID of the Budget.
 * @property name The name of the budget.
 * @property created The creation time of the budget expressed as UTC epoch timestamp in milliseconds.
 * @property amount The target amount for the budget.
 * @property periodicityType Tells whether the budget is recurring or one off type. Using this field it&#39;s possible to see which of the field &#x60;recurringPeriodicity&#x60; or &#x60;oneOffPeriodicity&#x60; is set.
 * @property recurringPeriodicity Periodicity configuration for a &#x60;RECURRING&#x60; budget.
 * @property oneOffPeriodicity Periodicity configuration for a &#x60;ONE_OFF&#x60; budget.
 * @property archived Indicates if the budget has state archived or not.
 * @property filter The filter defining the budget and which transactions that is included in it. The configured fields of the filter are applied as logical and operator (intersection).
 */
@JsonClass(generateAdapter = true)
data class Budget(
    @Json(name = "id") @field:Json(name = "id") var id: String? = null,
    @Json(name = "name") @field:Json(name = "name") var name: String? = null,
    @Json(name = "created") @field:Json(name = "created") var created: Long? = null,
    @Json(name = "amount") @field:Json(name = "amount") var amount: CurrencyDenominatedAmount? = null,
    @Json(name = "periodicityType") @field:Json(name = "periodicityType") var periodicityType: Budget.PeriodicityTypeEnum? = null,
    @Json(name = "recurringPeriodicity") @field:Json(name = "recurringPeriodicity") var recurringPeriodicity: RecurringPeriodicity? = null,
    @Json(name = "oneOffPeriodicity") @field:Json(name = "oneOffPeriodicity") var oneOffPeriodicity: OneOffPeriodicity? = null,
    @Json(name = "archived") @field:Json(name = "archived") var archived: Boolean? = null,
    @Json(name = "filter") @field:Json(name = "filter") var filter: Filter? = null
) {
    /**
     * Tells whether the budget is recurring or one off type. Using this field it's possible to see which of the field `recurringPeriodicity` or `oneOffPeriodicity` is set.
     * Values: ONE_OFF, RECURRING
     */
    @JsonClass(generateAdapter = false)
    enum class PeriodicityTypeEnum(val value: String) {
        @Json(name = "ONE_OFF") ONE_OFF("ONE_OFF"),
        @Json(name = "RECURRING") RECURRING("RECURRING")
    }
}
