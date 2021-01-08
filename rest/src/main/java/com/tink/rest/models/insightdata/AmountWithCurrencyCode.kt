package com.tink.rest.models.insightdata

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AmountWithCurrencyCode(
    @Json(name = "amount")
    val amount: Double,
    @Json(name = "currencyCode")
    val currencyCode: String
)
