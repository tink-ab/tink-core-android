/**
 * NOTE: This class is auto generated by the Swagger Gradle Codegen for the following API: Tink API
 *
 * More info on this tool is available on https://github.com/Yelp/swagger-gradle-codegen
 */

package com.tink.rest.apis

import com.tink.rest.models.AccountListResponse
import com.tink.rest.models.AuthenticatedUser
import com.tink.rest.models.SignableOperation
import com.tink.rest.models.SourceDestinationValidation
import com.tink.rest.models.Transfer
import com.tink.rest.models.TransferResponse
import retrofit2.http.GET
import retrofit2.http.POST

@JvmSuppressWildcards
interface TransferApi {
    /**
     * Create a new transfer
     * Creates a new transfer, returning a `SignableOperation` object.
     * The endpoint is owned by defaultname service owner
     * @param transfer The transfer object to create. (optional)
     */
    @POST("/api/v1/transfer")
    suspend fun createTransfer(
        @retrofit2.http.Body transfer: Transfer
    ): SignableOperation

    /**
     * Signing status of Transfer
     * Get the SignableOperation object of the transfer.
     * The endpoint is owned by defaultname service owner
     * @param id The ID of the Transfer (required)
     * @param body (optional)
     */
    @GET("/api/v1/transfer/{id}/status")
    suspend fun getSignableOperation(
        @retrofit2.http.Path("id") id: String,
        @retrofit2.http.Body body: AuthenticatedUser
    ): SignableOperation

    /**
     * Get bank accounts
     * List bank accounts that can make payments. Note that if you are using a new account that has no saved  recipients, it won't be shown. You need to add a recipient via your internet bank first.
     * The endpoint is owned by defaultname service owner
     * @param destination Destinations to whom the accounts should be able to initiate transfers. Example: &#x60;/api/v1/transfer/accounts?destination[]&#x3D;se-bg://9008004&amp;destination[]&#x3D;se-bg://8007003&#x60;. (optional)
     */
    @GET("/api/v1/transfer/accounts")
    suspend fun getSourceAccounts(
        @retrofit2.http.Query("destination") destination: List<String>?
    ): AccountListResponse

    /**
     * Fetch data about a transfer
     * Fetches data about the current status of a given transfer.
     * The endpoint is owned by defaultname service owner
     * @param id The transfer&#39;s id. (required)
     */
    @GET("/api/v1/transfer/{id}/data")
    suspend fun getTransferData(
        @retrofit2.http.Path("id") id: String
    ): TransferResponse

    /**
     * Validates source account against destination patterns
     * This endpoint returns \"canTransfer\" : true if a user can transfer money from the given source account to the given destination account. It will return \"canTransfer\" : false otherwise. Request example : `/api/v1/transfer/source-destination-validation?source=se://83279234947790&destination=se://33001984`.
     * The endpoint is owned by defaultname service owner
     * @param source The transfer&#39;s source account. (required)
     * @param destination The transfer&#39;s destination account (required)
     */
    @GET("/api/v1/transfer/source-destination-validation")
    suspend fun isSourceDestinationValid(
        @retrofit2.http.Query("source") source: String,
        @retrofit2.http.Query("destination") destination: String
    ): SourceDestinationValidation
}
