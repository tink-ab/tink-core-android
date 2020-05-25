package com.tink.model.misc

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.math.BigDecimal

@Parcelize
data class ExactNumber(
    val unscaledValue: Long,
    val scale: Long
) : Comparable<ExactNumber>, Parcelable {

    constructor(double: Double) : this(BigDecimal.valueOf(double))
    constructor(long: Long) : this(long, 0)
    constructor(bigDecimal: BigDecimal) : this(
        bigDecimal.unscaledValue().toLong(),
        bigDecimal.scale().toLong()
    )

    fun toBigDecimal() = BigDecimal(unscaledValue.toBigInteger(), scale.toInt())

    override fun compareTo(other: ExactNumber): Int = toBigDecimal().compareTo(other.toBigDecimal())
}