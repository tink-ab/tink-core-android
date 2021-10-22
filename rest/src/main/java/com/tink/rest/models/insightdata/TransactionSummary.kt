package com.tink.rest.models.insightdata

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TransactionSummary(
    @Json(name = "commonTransactionsOverview")
    val commonTransactionsOverview: CommonTransactionsOverview,
    @Json(name = "totalExpenses")
    val totalExpenses: AmountWithCurrencyCode,
    @Json(name = "largestExpense")
    val largestExpense: LargestExpense)

