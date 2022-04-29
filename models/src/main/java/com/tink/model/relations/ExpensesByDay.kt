package com.tink.model.relations

import android.os.Parcelable
import com.tink.model.misc.Amount
import kotlinx.android.parcel.Parcelize
import java.time.LocalDate

@Parcelize
data class ExpensesByDay(
    val date: LocalDate,
    val totalAmount: Amount,
    val averageAmount: Amount
) : Parcelable
