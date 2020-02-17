package com.tink.model.statistic

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StatisticTree(
    val balancesByAccountId: Statistic,
    val balancesByAccountGroupType: Statistic,
    val leftToSpend: Statistic,
    val leftToSpendAverage: Statistic,
    val expensesByCategoryCode: Statistic,
    val incomeByCategoryCode: Statistic
) : Parcelable
