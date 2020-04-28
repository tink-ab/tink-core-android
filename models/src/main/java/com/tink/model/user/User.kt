package com.tink.model.user

data class User(val authorization: Authorization) {

    companion object {

        @JvmStatic
        fun fromAccessToken(accessToken: String) = User(
            Authorization.AccessToken(accessToken)
        )

        @JvmStatic
        fun fromSessionId(sessionId: String) = User(
            Authorization.AccessToken(sessionId)
        )
    }
}

sealed class Authorization {
    data class AccessToken(val accessToken: String) : Authorization()
    data class SessionId(val sessionId: String) : Authorization()
}
