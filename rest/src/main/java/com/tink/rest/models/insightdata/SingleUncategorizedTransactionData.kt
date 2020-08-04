package com.tink.rest.models.insightdata

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SingleUncategorizedTransactionData(
    @Json(name = "transactionId")
    val transactionId: String
)