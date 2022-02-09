package com.tink.model.credentials

import android.os.Parcelable
import com.tink.model.provider.RefreshProvider
import kotlinx.android.parcel.Parcelize
import org.threeten.bp.Instant

@Parcelize
data class RefreshCredential(
    val id: String,
    val provider: RefreshProvider,
    val sessionExpiryDate: Instant
) : Parcelable
