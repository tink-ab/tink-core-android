package com.tink.model.statistic

import android.os.Parcelable
import com.tink.model.misc.Amount
import com.tink.model.time.Period
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Statistic(
    val period: Period,
    val value: Amount,
    val children: Map<String, Statistic>
) : Parcelable
