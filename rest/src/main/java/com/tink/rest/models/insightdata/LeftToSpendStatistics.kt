package com.tink.rest.models.insightdata

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LeftToSpendStatistics(
    val averageLeftToSpend: AmountWithCurrencyCode,
    val createdAt: Long,
    val currentLeftToSpend: AmountWithCurrencyCode
)
