package com.tink.model.user

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserProfile(
    val locale: String,
    val market: String,
    val timeZone: String,
    val currency: String,
    val periodMode: PeriodMode
) : Parcelable {

    sealed class PeriodMode : Parcelable {

        @Parcelize
        object Monthly : PeriodMode()

        @Parcelize
        data class MonthlyAdjusted(val periodAdjustedDayOfMonth: Int) : PeriodMode()
    }
}
