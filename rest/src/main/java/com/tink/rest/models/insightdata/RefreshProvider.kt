package com.tink.rest.models.insightdata

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RefreshProvider(
    @Json(name = "name")
    val name: String,
    @Json(name = "displayName")
    val displayName: String
)
