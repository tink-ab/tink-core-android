package com.tink.rest.models.insightdata

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BudgetSummaryAchievedData(
    @Json(name = "achievedBudgets")
    val achievedBudgets: List<BudgetIdToPeriod>,
    @Json(name = "overspentBudgets")
    val overspentBudgets: List<BudgetIdToPeriod>,
    @Json(name = "periodUnit")
    val periodUnit: String,
    @Json(name = "savedAmount")
    val savedAmount: AmountWithCurrencyCode
)