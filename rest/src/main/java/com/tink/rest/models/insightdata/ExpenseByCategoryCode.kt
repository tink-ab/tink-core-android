package com.tink.rest.models.insightdata
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ExpenseByCategoryCode(
    @Json(name = "categoryCode")
    val categoryCode: String,
    @Json(name = "spentAmount")
    val spentAmount: AmountWithCurrencyCode
)