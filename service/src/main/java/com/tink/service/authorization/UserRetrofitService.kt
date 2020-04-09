package com.tink.service.authorization

import com.squareup.moshi.Json
import retrofit2.http.Body
import retrofit2.http.POST

internal interface UserRetrofitService {

    @POST("/api/v1/oauth/authorize")
    suspend fun authorize(
        @Body body: AuthorizationRequest
    ): AuthorizationResponse

    @POST("/link/v1/authentication/token")
    suspend fun authenticate(
        @Body body: AuthenticationRequest
    ): AuthenticationResponse

    @POST("/api/v1/user/anonymous")
    suspend fun createAnonymousUser(
        @Body body: CreateAnonymousUserRequest
    ): CreateAnonymousUserResponse

    data class AuthorizationRequest(
        @field:Json(name = "clientId") val clientId: String,
        @field:Json(name = "redirectUri") val redirectUri: String,
        @field:Json(name = "scope") val scope: String
    )

    data class AuthorizationResponse(
        @field:Json(name = "code") val authorizationCode: String
    )

    data class AuthenticationRequest(
        @field:Json(name = "code") val code: String
    )

    data class AuthenticationResponse(
        @field:Json(name = "accessToken") val accessToken: String,
        @field:Json(name = "Scope") val scope: String
    )
}

data class CreateAnonymousUserRequest(
    @field:Json(name = "market") val market: String,
    @field:Json(name = "locale") val locale: String,
    @field:Json(name = "origin") val origin: String? = null
)

class CreateAnonymousUserResponse(
    @field:Json(name = "access_token") val accessToken: String
)
