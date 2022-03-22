package com.tink.service.network

import com.tink.model.user.Authorization
import com.tink.model.user.User
import com.tink.service.authentication.UserEventBus
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

private const val AUTHORIZATION = "Authorization"
private const val DEVICE_ID_HEADER_NAME = "X-Tink-Device-ID"
private const val SDK_NAME_HEADER_NAME = "X-Tink-SDK-Name"
private const val SDK_VERSION_HEADER_NAME = "X-Tink-SDK-Version"
private const val OAUTH_CLIENT_ID_HEADER_NAME = "X-Tink-OAuth-Client-ID"
private const val REMOTE_ADDRESS = "X-Forwarded-For"
private const val USER_AGENT = "User-Agent"

var coreSdkInformation: SdkInformation? = null

internal class HeaderInterceptor(
    private val oauthClientId: String,
    userEventBus: UserEventBus,
    private val deviceId: String?
) : Interceptor {

    var user: User? = null

    init {
        userEventBus.subscribe { user = it }
    }

    private fun getAuthorization(): String? {
        return when (val authorization = user?.authorization) {
            is Authorization.AccessToken -> "Bearer ${authorization.accessToken}"
            is Authorization.SessionId -> "Session ${authorization.sessionId}"
            else -> null
        }
    }

    override fun intercept(chain: Interceptor.Chain): Response =
        chain.request()
            .newBuilder()
            .addHeader(OAUTH_CLIENT_ID_HEADER_NAME, oauthClientId)
            .addHeader(SDK_NAME_HEADER_NAME, coreSdkInformation?.sdkClient?.clientName.orEmpty())
            .addHeader(SDK_VERSION_HEADER_NAME, coreSdkInformation?.version.orEmpty())
            .addHeaderIfNotNull(AUTHORIZATION, getAuthorization())
            .addHeaderIfNotNull(DEVICE_ID_HEADER_NAME, deviceId)
            .build()
            .let(chain::proceed)

    private fun Request.Builder.addHeaderIfNotNull(key: String, value: String?) =
        value?.let { addHeader(key, it) } ?: this
}
