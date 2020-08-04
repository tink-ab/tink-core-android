package com.tink.rest.models.insightdata


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BudgetCloseNegativeData(
    @Json(name = "budgetId")
    val budgetId: String,
    @Json(name = "budgetPeriod")
    val budgetPeriod: BudgetPeriod,
    @Json(name = "currentTime")
    val currentTime: Long,
    @Json(name = "periodUnit")
    val periodUnit: String
)