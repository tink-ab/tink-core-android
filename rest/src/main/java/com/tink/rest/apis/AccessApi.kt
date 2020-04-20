package com.tink.rest.apis

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import retrofit2.http.Body
import retrofit2.http.POST

interface AccessApi {

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
}

@JsonClass(generateAdapter = true)
data class AuthorizationRequest(
    @field:Json(name = "clientId") val clientId: String,
    @field:Json(name = "redirectUri") val redirectUri: String,
    @field:Json(name = "scope") val scope: String
)

@JsonClass(generateAdapter = true)
data class AuthorizationResponse(
    @field:Json(name = "code") val authorizationCode: String
)

@JsonClass(generateAdapter = true)
data class AuthenticationRequest(
    @field:Json(name = "code") val code: String
)

@JsonClass(generateAdapter = true)
data class AuthenticationResponse(
    @field:Json(name = "accessToken") val accessToken: String,
    @field:Json(name = "Scope") val scope: String
)

@JsonClass(generateAdapter = true)
data class CreateAnonymousUserRequest(
    @field:Json(name = "market") val market: String,
    @field:Json(name = "locale") val locale: String,
    @field:Json(name = "origin") val origin: String? = null
)

@JsonClass(generateAdapter = true)
data class CreateAnonymousUserResponse(
    @field:Json(name = "access_token") val accessToken: String
)
