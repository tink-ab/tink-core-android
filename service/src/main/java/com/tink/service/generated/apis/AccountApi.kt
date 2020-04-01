/**
 * NOTE: This class is auto generated by the Swagger Gradle Codegen for the following API: Sample Pet Store App
 *
 * More info on this tool is available on https://github.com/Yelp/swagger-gradle-codegen
 */

package com.tink.service.generated.apis

import com.tink.service.generated.models.Account
import com.tink.service.generated.models.AccountListResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PUT

@JvmSuppressWildcards
interface AccountApi {
    /**
     * The endpoint is owned by defaultname service owner
     */
    @Headers(
        "X-Operation-ID: list"
    )
    @GET("/api/v1/accounts")
    @Deprecated(message = "Deprecated")
    suspend fun list(): List<Account>
    /**
     * List accounts
     * Returns an object with a list of the authenticated user's accounts.
     * The endpoint is owned by defaultname service owner
     */
    @Headers(
        "X-Operation-ID: listAccounts"
    )
    @GET("/api/v1/accounts/list")
    suspend fun listAccounts(): AccountListResponse
    /**
     * Update an Account
     * Updates mutable properties of an account. The following properties are possible to update: accountExclusion, accountNumber, excluded, favored, name, type
     * The endpoint is owned by defaultname service owner
     * @param id The ID of the account (required)
     * @param body The updated account object (required)
     */
    @Headers(
        "X-Operation-ID: update",
        "Content-Type: application/json"
    )
    @PUT("/api/v1/accounts/{id}")
    suspend fun update(
        @retrofit2.http.Path("id") id: String,
        @retrofit2.http.Body body: Account
    ): Account
}
