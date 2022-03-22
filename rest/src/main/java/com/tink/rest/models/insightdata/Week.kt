package com.tink.rest.models.insightdata

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Week(
    @Json(name = "week")
    val week: Int,
    @Json(name = "year")
    val year: Int
)
