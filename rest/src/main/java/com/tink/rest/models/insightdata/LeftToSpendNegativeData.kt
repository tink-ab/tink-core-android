package com.tink.rest.models.insightdata

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LeftToSpendNegativeData(
    @Json(name = "createdAt")
    val createdAt: Long,
    @Json(name = "leftToSpend")
    val leftToSpend: AmountWithCurrencyCode,
    @Json(name = "month")
    val month: Month
)