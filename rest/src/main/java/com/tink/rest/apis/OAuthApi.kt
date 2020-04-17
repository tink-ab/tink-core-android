/**
 * NOTE: This class is auto generated by the Swagger Gradle Codegen for the following API: Tink API
 *
 * More info on this tool is available on https://github.com/Yelp/swagger-gradle-codegen
 */

package com.tink.rest.apis

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.tink.rest.models.OAuth2AuthenticationTokenResponse
import com.tink.rest.models.OAuth2AuthorizeResponse
import com.tink.rest.models.OAuth2Client
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

@JvmSuppressWildcards
interface OAuthApi {
    /**
     * Create a delegated authorization code
     * Creates a delegated authorization code for a user with requested scopes.
     * @param idHint Human readable identifier to prevent user from authorizing access for another user (required)
     * @param actorClientId The unique identifier of the client which is allowed to use the authorization request. Actor client existence is not validated (required)
     * @param body (optional)
     * @param userId User ID. Cannot be specified at the same time as &#x60;external_user_id&#x60;. (optional)
     * @param externalUserId External user ID set by the client on user creation. Cannot be specified at the same time as &#x60;user_id&#x60;. (optional)
     * @param scope Scopes to delegate access for (optional)
     */
    @retrofit2.http.FormUrlEncoded
    @POST("/api/v1/oauth/authorization-grant/delegate")
    suspend fun authorizationDelegate(
        @retrofit2.http.Field("id_hint") idHint: String,
        @retrofit2.http.Field("actor_client_id") actorClientId: String,
        @retrofit2.http.Body body: OAuth2Client,
        @retrofit2.http.Field("user_id") userId: String?,
        @retrofit2.http.Field("external_user_id") externalUserId: String?,
        @retrofit2.http.Field("scope") scope: String?
    ): Response<Unit>
    /**
     * Create an authorization
     * Creates an authorization for the given user ID or external user ID with requested scopes and returns the authorization code.
     * @param scope The requested OAuth scopes (required)
     * @param body (optional)
     * @param userId User ID. Cannot be specified at the same time as &#x60;external_user_id&#x60;. (optional)
     * @param externalUserId External user ID set by the client on user creation. Cannot be specified at the same time as &#x60;user_id&#x60;. (optional)
     */
    @retrofit2.http.FormUrlEncoded
    @POST("/api/v1/oauth/authorization-grant")
    suspend fun authorizationGrant(
        @retrofit2.http.Field("scope") scope: String,
        @retrofit2.http.Body body: OAuth2Client,
        @retrofit2.http.Field("user_id") userId: String?,
        @retrofit2.http.Field("external_user_id") externalUserId: String?
    ): OAuth2AuthorizeResponse
    /**
     * Revoke all tokens
     * Revokes all access and refresh tokens for a given user ID or external user ID.
     * @param body (optional)
     * @param userId The user ID (optional)
     * @param externalUserId The external user ID (optional)
     */
    @retrofit2.http.FormUrlEncoded
    @POST("/api/v1/oauth/revoke-all")
    suspend fun revokeAll(
        @retrofit2.http.Body body: OAuth2Client,
        @retrofit2.http.Field("user_id") userId: String?,
        @retrofit2.http.Field("external_user_id") externalUserId: String?
    ): Response<Unit>
    /**
     * Get an authorization token
     * Exchange an authorization code or a refresh token for authorization tokens. The authorization tokens are used to access API resources on the end-user's behalf.
     * @param clientId The OAuth client ID. (required)
     * @param clientSecret The client secret of your third-party application. (required)
     * @param grantType The grant type. (required)
     * @param code The one-time code that was returned from the authorization flow. Can be omitted if grant types refresh_token or client_credentials are used. (optional)
     * @param refreshToken The refresh token to be used to get a new access token. Can be omitted if grant types authorization_code or client_credentials are used. (optional)
     * @param scope The requested scope when using client credentials. Can be omitted if grant types authorization_code or refresh_token are used. (optional)
     */
    @retrofit2.http.FormUrlEncoded
    @POST("/api/v1/oauth/token")
    suspend fun token(
        @retrofit2.http.Field("client_id") clientId: String,
        @retrofit2.http.Field("client_secret") clientSecret: String,
        @retrofit2.http.Field("grant_type") grantType: String,
        @retrofit2.http.Field("code") code: String?,
        @retrofit2.http.Field("refresh_token") refreshToken: String?,
        @retrofit2.http.Field("scope") scope: String?
    ): OAuth2AuthenticationTokenResponse

    @POST("api/v1/oauth/describe")
    suspend fun describe(
        @Body describeOAuth2ClientRequest: DescribeOAuth2ClientRequest
    ): DescribeOAuth2ClientResponse
}

@JsonClass(generateAdapter = true)
data class DescribeOAuth2ClientRequest(
    val clientId: String,
    val redirectUri: String,
    val scopes: String
)

@JsonClass(generateAdapter = true)
data class DescribeOAuth2ClientResponse(
    @field:Json(name = "clientName") val clientName: String,
    @field:Json(name = "clientUrl") val clientUrl: String,
    @field:Json(name = "clientIconUrl") val clientIconUrl: String,
    @field:Json(name = "embeddedAllowed") val embeddedAllowed: Boolean,
    @field:Json(name = "scopesDescriptionsList") val scopeDescriptions: List<ScopeDescription>,
    @field:Json(name = "verified") val verified: Boolean,
    @field:Json(name = "aggregator") val aggregator: Boolean
)

@JsonClass(generateAdapter = true)
data class ScopeDescription(
    @field:Json(name = "title") val title: String,
    @field:Json(name = "description") val description: String
)
