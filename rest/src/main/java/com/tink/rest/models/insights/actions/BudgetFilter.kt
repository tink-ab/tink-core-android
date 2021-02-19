package com.tink.rest.models.insights.actions

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BudgetFilter(
    @Json(name = "categories")
    val categories: List<String>? = null,
    @Json(name = "accounts")
    val accounts: List<String>? = null
)