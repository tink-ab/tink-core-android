package com.tink.rest.models.insightdata

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RefreshCredential(
    @Json(name = "id")
    val id: String,
    @Json(name = "provider")
    val provider: RefreshProvider,
    @Json(name = "sessionExpiryDate")
    val sessionExpiryDate: Long
)
