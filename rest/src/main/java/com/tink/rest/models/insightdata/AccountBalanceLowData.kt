package com.tink.rest.models.insightdata

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AccountBalanceLowData(
    @Json(name = "accountId")
    val accountId: String,
    @Json(name = "balance")
    val balance: AmountWithCurrencyCode
)
