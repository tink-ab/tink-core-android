package com.tink.model.provider

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RefreshProvider(
    val name: String,
    val displayName: String
) : Parcelable
