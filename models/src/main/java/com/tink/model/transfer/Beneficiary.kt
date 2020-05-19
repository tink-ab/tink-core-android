package com.tink.model.transfer

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Beneficiary(
    val accountId: String,
    val accountNumber: String,
    val name: String,
    val type: String
) : Parcelable
