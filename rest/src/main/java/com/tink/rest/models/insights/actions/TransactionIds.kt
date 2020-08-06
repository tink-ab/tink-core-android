package com.tink.rest.models.insights.actions

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TransactionIds(
    val transactionIds: List<String>
)
