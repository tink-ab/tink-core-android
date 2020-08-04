package com.tink.rest.models.insightdata


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeeklySummaryExpensesByDayData(
    @Json(name = "expenseStatisticsByDay")
    val expenseStatisticsByDay: List<ExpenseStatisticsByDay>,
    @Json(name = "week")
    val week: Week
)