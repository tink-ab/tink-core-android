package com.tink.rest.models.insightdata


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Month(
    @Json(name = "month")
    val month: Int,
    @Json(name = "year")
    val year: Int
)