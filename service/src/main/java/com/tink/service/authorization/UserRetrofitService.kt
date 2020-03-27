package com.tink.service.authorization

import com.google.gson.annotations.SerializedName
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

internal interface UserRetrofitService {

    @POST("/api/v1/oauth/authorize")
    fun authorize(
        @Header("Authorization") auth: String,
        @Body body: AuthorizationRequest
    ): Single<AuthorizationResponse>

    @POST("/link/v1/authentication/token")
    fun authenticate(
        @Body body: AuthenticationRequest
    ): Single<AuthenticationResponse>

    data class AuthorizationRequest(
        @SerializedName("clientId") val clientId: String,
        @SerializedName("redirectUri") val redirectUri: String,
        @SerializedName("scope") val scope: String
    )

    data class AuthorizationResponse(
        @SerializedName("code") val authorizationCode: String
    )

    data class AuthenticationRequest(
        @SerializedName("code") val code: String
    )

    data class AuthenticationResponse(
        @SerializedName("accessToken") val accessToken: String,
        @SerializedName("Scope") val scope: String
    )
}
