/**
 * NOTE: This class is auto generated by the Swagger Gradle Codegen for the following API: Sample Pet Store App
 *
 * More info on this tool is available on https://github.com/Yelp/swagger-gradle-codegen
 */

package com.tink.service.generated.apis

import com.tink.service.generated.models.CallbackRelayedRequest
import com.tink.service.generated.models.CreateCredentialsRequest
import com.tink.service.generated.models.Credentials
import com.tink.service.generated.models.CredentialsListResponse
import com.tink.service.generated.models.ManualAuthenticationRequest
import com.tink.service.generated.models.RefreshCredentialsRequest
import com.tink.service.generated.models.SupplementalInformation
import com.tink.service.generated.models.UpdateCredentialsRequest
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT

@JvmSuppressWildcards
interface CredentialsApi {
    /**
     * Create credentials
     * Creates the Credentials for the user. The create request will trigger a refresh towards the provider.
     * The endpoint is owned by defaultname service owner
     * @param body The credentials to create. Only providerName and fields are required. (optional)
     * @param items The data types to aggregate from the provider. Multiple items are allowed and are passed as: items&#x3D;item1&amp;items&#x3D;item2. If omitted, all data types are aggregated. (optional)
     */
    @Headers(
        "X-Operation-ID: create",
        "Content-Type: application/json"
    )
    @POST("/api/v1/credentials")
    suspend fun create(
        @retrofit2.http.Body body: CreateCredentialsRequest,
        @retrofit2.http.Query("items") items: List<String>?
    ): Credentials
    /**
     * Delete credentials
     * Deletes the given credentials. The deletion is partly done asynchronously.
     * The endpoint is owned by defaultname service owner
     * @param id The internal identifier of the credentials to delete (required)
     */
    @Headers(
        "X-Operation-ID: delete"
    )
    @DELETE("/api/v1/credentials/{id}")
    suspend fun delete(
        @retrofit2.http.Path("id") id: String
    ): Unit
    /**
     * Get credentials
     * Gets credentials by ID.
     * The endpoint is owned by defaultname service owner
     * @param id The internal identifier of the credentials to get (required)
     */
    @Headers(
        "X-Operation-ID: get"
    )
    @GET("/api/v1/credentials/{id}")
    suspend fun get(
        @retrofit2.http.Path("id") id: String
    ): Credentials
    /**
     * List credentials
     * List all credentials for the user.
     * The endpoint is owned by defaultname service owner
     */
    @Headers(
        "X-Operation-ID: getCredentialsList"
    )
    @GET("/api/v1/credentials/list")
    suspend fun getCredentialsList(): CredentialsListResponse
    /**
     * Manual authenticate of credentials
     * Triggers a full authentication flow to renew refresh tokens with ASPSPs. This endpoint is limited to credentials connected to providers of access type `OPEN_BANKING`. This endpoint only triggers authentication, thus a full credentials refresh will not be executed.
     * The endpoint is owned by defaultname service owner
     * @param id The internal identifier of the &#x60;Credentials&#x60; object to authenticate. (required)
     * @param body (optional)
     */
    @Headers(
        "X-Operation-ID: manualAuthentication",
        "Content-Type: application/json"
    )
    @POST("/api/v1/credentials/{id}/authenticate")
    suspend fun manualAuthentication(
        @retrofit2.http.Path("id") id: String,
        @retrofit2.http.Body body: ManualAuthenticationRequest
    ): Unit
    /**
     * Get QR code
     * QR code for authentication flows such as Mobile BankID as base64 encoded PNG. Includes `data:image/png;base64,`.
     * The endpoint is owned by defaultname service owner
     * @param id (required)
     */
    @Headers(
        "X-Operation-ID: qrAsBase64",
        "Content-Type: application/json"
    )
    @GET("/api/v1/credentials/{id}/qr")
    suspend fun qrAsBase64(
        @retrofit2.http.Path("id") id: String
    ): String
    /**
     * Refresh credentials
     * Refreshes the specified credentials. It's only possible to refresh a credential every tenth minute, given that the latest refresh was successful.
     * The endpoint is owned by defaultname service owner
     * @param id The internal identifier of the &#x60;Credentials&#x60; object to refresh. (required)
     * @param body (optional)
     * @param items The data types to aggregate from the Provider. Multiple items are allowed. If omitted, all data types are aggregated. (optional)
     * @param optIn Set to true to trigger an opt-in of accounts before doing the refresh. Today only available for enterprise customers. (optional)
     */
    @Headers(
        "X-Operation-ID: refresh",
        "Content-Type: application/json"
    )
    @POST("/api/v1/credentials/{id}/refresh")
    suspend fun refresh(
        @retrofit2.http.Path("id") id: String,
        @retrofit2.http.Body body: RefreshCredentialsRequest,
        @retrofit2.http.Query("items") items: List<String>?,
        @retrofit2.http.Query("optIn") optIn: Boolean?
    ): Unit
    /**
     * Add Supplemental Information
     * Adds supplemental information to an authentication.
     * The endpoint is owned by defaultname service owner
     * @param id (required)
     * @param body The supplemental information. (required)
     */
    @Headers(
        "X-Operation-ID: supplemental",
        "Content-Type: application/json"
    )
    @POST("/api/v1/credentials/{id}/supplemental-information")
    suspend fun supplemental(
        @retrofit2.http.Path("id") id: String,
        @retrofit2.http.Body body: SupplementalInformation
    ): Unit
    /**
     * Third-party callback with redirect
     * Send callback information from an ASPSP. This endpoint will return the registered redirect uri as response.
     * The endpoint is owned by defaultname service owner
     * @param body The callback response from the ASPSP with JSON format. (required)
     */
    @Headers(
        "X-Operation-ID: thirdPartyCallbackRelayedPost",
        "Content-Type: application/json"
    )
    @POST("/api/v1/credentials/third-party/callback/relayed")
    suspend fun thirdPartyCallbackRelayedPost(
        @retrofit2.http.Body body: CallbackRelayedRequest
    ): Unit
    /**
     * Modify credentials
     * Modify the specified credentials.
     * The endpoint is owned by defaultname service owner
     * @param id The internal identifier of the credentials to change (required)
     * @param body The new credentials object. (required)
     */
    @Headers(
        "X-Operation-ID: update",
        "Content-Type: application/json"
    )
    @PUT("/api/v1/credentials/{id}")
    suspend fun update(
        @retrofit2.http.Path("id") id: String,
        @retrofit2.http.Body body: UpdateCredentialsRequest
    ): Credentials
}
