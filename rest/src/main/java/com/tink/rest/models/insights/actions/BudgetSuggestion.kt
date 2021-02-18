package com.tink.rest.models.insights.actions

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.tink.rest.models.OneOffPeriodicity
import com.tink.rest.models.RecurringPeriodicity
import com.tink.rest.models.insightdata.AmountWithCurrencyCode

@JsonClass(generateAdapter = true)
data class BudgetSuggestion(
    @Json(name = "filter")
    val filter: BudgetFilter? = null,
    @Json(name = "amount")
    val amount: AmountWithCurrencyCode? = null,
    @Json(name = "periodicityType")
    val periodicityType: BudgetPeriodicityTypeEnum? = null,
    @Json(name = "recurringPeriodicityData")
    val recurringPeriodicityData: RecurringPeriodicity? = null,
    @Json(name = "oneOffPeriodicityData")
    val oneOffPeriodicityData: OneOffPeriodicity? = null
) {
    @JsonClass(generateAdapter = false)
    enum class BudgetPeriodicityTypeEnum(val value: String) {
        @Json(name = "BUDGET_PERIODICITY_TYPE_ONE_OFF") BUDGET_PERIODICITY_TYPE_ONE_OFF("BUDGET_PERIODICITY_TYPE_ONE_OFF"),
        @Json(name = "BUDGET_PERIODICITY_TYPE_RECURRING") BUDGET_PERIODICITY_TYPE_RECURRING("BUDGET_PERIODICITY_TYPE_RECURRING")
    }
}