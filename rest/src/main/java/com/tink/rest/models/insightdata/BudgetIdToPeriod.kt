package com.tink.rest.models.insightdata


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BudgetIdToPeriod(
    @Json(name = "budgetId")
    val budgetId: String,
    @Json(name = "budgetPeriod")
    val budgetPeriod: BudgetPeriod
)