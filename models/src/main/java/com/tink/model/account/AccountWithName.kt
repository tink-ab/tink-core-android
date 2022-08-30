package com.tink.model.account

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AccountWithName(
    val accountId: String,
    val accountName: String
) : Parcelable
