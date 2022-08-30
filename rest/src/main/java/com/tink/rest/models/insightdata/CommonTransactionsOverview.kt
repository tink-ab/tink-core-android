package com.tink.rest.models.insightdata

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CommonTransactionsOverview(
    @Json(name = "mostCommonTransactionDescription")
    val mostCommonTransactionDescription: String,
    @Json(name = "totalNumberOfTransactions")
    val totalNumberOfTransactions: Int,
    @Json(name = "mostCommonTransactionCount")
    val mostCommonTransactionCount: Int
)
