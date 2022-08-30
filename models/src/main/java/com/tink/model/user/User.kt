package com.tink.model.user

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(val authorization: Authorization) : Parcelable {

    companion object {

        @JvmStatic
        fun fromAccessToken(accessToken: String) = User(
            Authorization.AccessToken(accessToken)
        )

        @JvmStatic
        fun fromSessionId(sessionId: String) = User(
            Authorization.SessionId(sessionId)
        )
    }
}

sealed class Authorization : Parcelable {

    @Parcelize
    data class AccessToken(val accessToken: String) : Authorization()

    @Parcelize
    data class SessionId(val sessionId: String) : Authorization()
}
