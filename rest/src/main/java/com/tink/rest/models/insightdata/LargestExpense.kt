package com.tink.rest.models.insightdata

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LargestExpense(
    @Json(name = "date")
    val date: Long,
    @Json(name = "amount")
    val amount: AmountWithCurrencyCode,
    @Json(name = "description")
    val description: String,
    @Json(name = "id")
    val id: String)