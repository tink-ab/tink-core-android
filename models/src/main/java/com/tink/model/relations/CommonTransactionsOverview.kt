package com.tink.model.relations

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CommonTransactionsOverview(
    val mostCommonTransactionDescription: String,
    val totalNumberOfTransactions: Int,
    val mostCommonTransactionCount: Int
) : Parcelable
