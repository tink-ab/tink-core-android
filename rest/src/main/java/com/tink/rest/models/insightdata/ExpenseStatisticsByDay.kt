package com.tink.rest.models.insightdata

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ExpenseStatisticsByDay(
    @Json(name = "date")
    val date: String,
    @Json(name = "expenseStatistics")
    val expenseStatistics: ExpenseStatistics
)