package com.tink.rest.models.insightdata

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ExpenseStatisticsByDay(
    @Json(name = "date")
    val date: List<Int>,
    @Json(name = "expenseStatistics")
    val expenseStatistics: ExpenseStatistics
)
