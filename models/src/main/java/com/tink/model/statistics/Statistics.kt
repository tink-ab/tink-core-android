package com.tink.model.statistics

import android.os.Parcelable
import com.tink.model.misc.Amount
import com.tink.model.time.Period
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Statistics(
    val identifier: String,
    val type: String,
    val period: Period,
    val value: Amount
) : Parcelable
