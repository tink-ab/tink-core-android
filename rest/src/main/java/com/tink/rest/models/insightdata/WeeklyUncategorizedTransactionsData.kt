package com.tink.rest.models.insightdata

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeeklyUncategorizedTransactionsData(
    @Json(name = "transactionIds")
    val transactionIds: List<String>,
    @Json(name = "week")
    val week: Week
)