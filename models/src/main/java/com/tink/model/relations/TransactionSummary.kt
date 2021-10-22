package com.tink.model.relations

import android.os.Parcelable
import com.tink.model.misc.Amount
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TransactionSummary(
    val commonTransactionsOverview: CommonTransactionsOverview,
    val totalExpenses: Amount,
    val largestExpense: LargestExpense
) : Parcelable
