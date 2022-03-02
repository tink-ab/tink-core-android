package com.tink.rest.models.insightdata

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AccountWithName(
    @Json(name = "accountId")
    val accountId: String,
    @Json(name = "accountName")
    val accountName: String
)
