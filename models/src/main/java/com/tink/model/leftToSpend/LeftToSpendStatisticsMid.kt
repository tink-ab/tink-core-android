package com.tink.model.leftToSpend

import android.os.Parcelable
import com.tink.model.misc.Amount
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LeftToSpendStatisticsMid(
    val averageLeftToSpend: Amount,
    val createdAt: Long,
    val currentLeftToSpend: Amount
) : Parcelable
