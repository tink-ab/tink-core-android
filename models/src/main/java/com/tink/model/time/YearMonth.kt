package com.tink.model.time

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class YearMonth(val year: Int, val month: Int) : Parcelable
