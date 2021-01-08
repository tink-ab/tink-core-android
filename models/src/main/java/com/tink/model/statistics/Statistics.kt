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
        EXPENSES_BY_CATEGORY("expenses-by-category"),
        INCOME_BY_CATEGORY("income-by-category"),
        UNKNOWN("")
    }
}
