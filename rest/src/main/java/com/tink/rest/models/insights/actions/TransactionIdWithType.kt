package com.tink.rest.models.insights.actions

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TransactionIdWithType(
    @Json(name = "id")
    val id: String,
    @Json(name = "type")
    val type: String
)
