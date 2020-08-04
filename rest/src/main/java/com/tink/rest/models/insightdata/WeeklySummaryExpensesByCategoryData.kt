package com.tink.rest.models.insightdata
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeeklySummaryExpensesByCategoryData(
    @Json(name = "expensesByCategory")
    val expensesByCategory: List<ExpenseByCategoryCode>,
    @Json(name = "week")
    val week: Week
)