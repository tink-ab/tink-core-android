package com.tink.model.time

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.threeten.bp.Instant

@Parcelize
data class Period(
    val day: Int?,
    val week: Int?,
    val month: Int?,
    val year: Int?,
    val start: Instant?,
    val end: Instant?
) : Parcelable
