package com.tink.rest.models.insightdata


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ExpenseStatistics(
    @Json(name = "averageAmount")
    val averageAmount: AmountWithCurrencyCode,
    @Json(name = "totalAmount")
    val totalAmount: AmountWithCurrencyCode
)