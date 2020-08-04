package com.tink.rest.models.insightdata

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BudgetPeriod(
    @Json(name = "budgetAmount")
    val budgetAmount: AmountWithCurrencyCode,
    @Json(name = "end")
    val end: Long,
    @Json(name = "spentAmount")
    val spentAmount: AmountWithCurrencyCode,
    @Json(name = "start")
    val start: Long
)
