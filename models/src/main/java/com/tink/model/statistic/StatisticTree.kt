package com.tink.model.statistic

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StatisticTree(
    val expensesByCategoryCode: StatisticsGroupNode,
    val incomeByCategoryCode: StatisticsGroupNode
) : Parcelable
