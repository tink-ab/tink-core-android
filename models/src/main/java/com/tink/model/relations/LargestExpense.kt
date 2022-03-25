package com.tink.model.relations

import android.os.Parcelable
import com.tink.model.misc.Amount
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LargestExpense(
    val date: Long,
    val amount: Amount,
    val description: String,
    val id: String
) : Parcelable
