package com.tink.model.user

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserProfile(
    val locale: String,
    val market: String,
    val timeZone: String,
    val currency: String,
    val periodAdjustedDay: Int,
    val periodMode: PeriodMode
) : Parcelable {

    enum class PeriodMode {
        UNKNOWN,
        MONTHLY,
        MONTHLY_ADJUSTED
    }
}
