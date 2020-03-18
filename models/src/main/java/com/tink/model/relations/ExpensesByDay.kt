package com.tink.model.relations

import android.os.Parcelable
import com.tink.model.misc.Amount
import kotlinx.android.parcel.Parcelize
import org.threeten.bp.Instant

@Parcelize
data class ExpensesByDay(
    val date: Instant,
    val totalAmount: Amount,
    val averageAmount: Amount
) : Parcelable
