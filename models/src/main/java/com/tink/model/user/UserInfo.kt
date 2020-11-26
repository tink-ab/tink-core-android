package com.tink.model.user

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.threeten.bp.Instant

@Parcelize
data class UserInfo(
    val created: Instant = Instant.EPOCH,
    val id: String,
    val appId: String,
    val profile: UserProfile,
    val flags: List<String> = emptyList(),
    val username: String? = null,
    val nationalId: String? = null
) : Parcelable