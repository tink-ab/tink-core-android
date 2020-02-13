package com.tink.model.statistic

import android.os.Parcelable
import com.tink.model.misc.Amount
import com.tink.model.misc.ExactNumber
import com.tink.model.time.Period
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Statistic(
    val period: Period,
    val statisticValue: StatisticValue,
    val children: Map<String, Statistic>
) : Parcelable

sealed class StatisticValue : Parcelable

@Parcelize
data class AmountStatisticValue(val amount: Amount) : StatisticValue()

@Parcelize
data class ExactNumberStatisticValue(val exactNumber: ExactNumber) : StatisticValue()

