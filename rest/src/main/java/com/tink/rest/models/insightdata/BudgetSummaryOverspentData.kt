package com.tink.rest.models.insightdata

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BudgetSummaryOverspentData(
    @Json(name = "achievedBudgets")
    val achievedBudgets: List<BudgetIdToPeriod>,
    @Json(name = "overspentAmount")
    val overspentAmount: AmountWithCurrencyCode,
    @Json(name = "overspentBudgets")
    val overspentBudgets: List<BudgetIdToPeriod>,
    @Json(name = "periodUnit")
    val periodUnit: String
)