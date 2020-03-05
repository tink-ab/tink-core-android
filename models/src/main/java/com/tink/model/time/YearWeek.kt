package com.tink.model.time

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class YearWeek(val year: Int, val week: Int) : Parcelable