package com.tink.model.time

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.threeten.bp.Instant

sealed class Period : Parcelable {
    abstract val identifier: String
    abstract val start: Instant
    abstract val end: Instant
}

@Parcelize
data class YearPeriod(
    val year: Int,
    override val identifier: String,
    override val start: Instant,
    override val end: Instant
) : Period()

@Parcelize
data class MonthPeriod(
    val monthOfYear: Int,
    val year: Int,
    override val identifier: String,
    override val start: Instant,
    override val end: Instant
) : Period() {
    init {
        require(monthOfYear in 1..12)
    }
}

@Parcelize
data class DayPeriod(
    val dayOfMonth: Int,
    val monthOfYear: Int,
    val year: Int,
    override val identifier: String,
    override val start: Instant,
    override val end: Instant
) : Period() {
    init {
        require(dayOfMonth in 1..31)
        require(monthOfYear in 1..12)
    }
}

@Parcelize
data class WeekPeriod(
    val weekOfYear: Int,
    val year: Int,
    override val identifier: String,
    override val start: Instant,
    override val end: Instant
) : Period()
