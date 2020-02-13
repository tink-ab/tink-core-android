package com.tink.model.relations

import android.os.Parcelable
import com.tink.model.misc.Amount
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AmountByCategory(val categoryCode: String, val amount: Amount) : Parcelable
