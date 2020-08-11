package com.tink.model.statistics

import android.os.Parcelable
import com.tink.model.misc.Amount
import com.tink.model.time.Period
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Statistics(
    val identifier: String,
    val type: Type,
    val period: Period,
    val value: Amount
) : Parcelable {

    enum class Type(val value: String) {
        EXPENSES("expenses-by-category"),
        INCOME("income-by-category"),
        UNKNOWN("")
    }
}
