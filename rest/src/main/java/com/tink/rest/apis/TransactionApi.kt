/**
 * NOTE: This class is auto generated by the Swagger Gradle Codegen for the following API: Tink API
 *
 * More info on this tool is available on https://github.com/Yelp/swagger-gradle-codegen
 */

package com.tink.rest.apis

import com.tink.rest.models.CategorizeTransactionsListRequest
import com.tink.rest.models.DeleteTransactionPartResponse
import com.tink.rest.models.LinkTransactionsRequest
import com.tink.rest.models.LinkTransactionsResponse
import com.tink.rest.models.SimilarTransactionsResponse
import com.tink.rest.models.SuggestTransactionsResponse
import com.tink.rest.models.Transaction
import com.tink.rest.models.TransactionLinkSuggestionResponse
import com.tink.rest.models.UpdateTransactionLinkRequest
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

@JvmSuppressWildcards
interface TransactionApi {
    /**
     * Change category of transactions
     * Changes category of the supplied list of transactions to the supplied category
     * @param body Object holding a list of new categories and the transactions to be categorized (required)
     */
    @PUT("/api/v1/transactions/categorize-multiple")
    suspend fun categorize(
        @retrofit2.http.Body body: CategorizeTransactionsListRequest
    ): Response<Unit>
    /**
     * Delete transaction part
     * If the part is linked to another transaction, the bilateral link is removed as well (i.e. the counterpart will be removed too, if found).
     * @param id The ID of the transaction to which the part belongs to. (required)
     * @param partId The part ID to delete. (required)
     */
    @DELETE("/api/v1/transactions/{id}/part/{partId}")
    suspend fun deletePart(
        @retrofit2.http.Path("id") id: String,
        @retrofit2.http.Path("partId") partId: String
    ): DeleteTransactionPartResponse
    /**
     * Get one transaction
     * Returns a transaction matching the requested id
     * @param id The ID of the transaction (required)
     */
    @GET("/api/v1/transactions/{id}")
    suspend fun getTransaction(
        @retrofit2.http.Path("id") id: String
    ): Transaction
    /**
     * Link transactions
     * Link two transactions, creating a transaction part for each transaction and netting out the amounts. The transactions are required to have different signs (i.e. one income and one expense). If one transaction is -300 and the other is +100, the common disposable amount is 100.
     * @param id The ID of the first transaction to link. (required)
     * @param counterpartTransactionId The ID of the other transaction (the counterpart) to link. (required)
     * @param body Object holding the required amount for transaction linking (required)
     */
    @POST("/api/v1/transactions/{id}/link/{counterpartTransactionId}")
    suspend fun link(
        @retrofit2.http.Path("id") id: String,
        @retrofit2.http.Path("counterpartTransactionId") counterpartTransactionId: String,
        @retrofit2.http.Body body: LinkTransactionsRequest
    ): LinkTransactionsResponse
    /**
     * Get counterpart suggestions
     * Returns suggestions for potential counterpart expenses for a reimbursement.
     * @param id The ID of the transaction to get suggestions for (required)
     * @param limit Max number of suggestions returned. (optional, default to 5)
     */
    @GET("/api/v1/transactions/{id}/link/suggest")
    suspend fun linkSuggest(
        @retrofit2.http.Path("id") id: String,
        @retrofit2.http.Query("limit") limit: Int?
    ): TransactionLinkSuggestionResponse
    /**
     * Get similar transactions
     * Returns an object holding a list of transactions similar to the supplied transaction based on description and a list of statistics summarizing these transactions
     * @param id The ID of the transaction (required)
     * @param categoryId Return similar of this category (optional)
     * @param includeSelf Include the supplied transaction in response (optional)
     */
    @GET("/api/v1/transactions/{id}/similar")
    suspend fun similar(
        @retrofit2.http.Path("id") id: String,
        @retrofit2.http.Query("categoryId") categoryId: String? = null,
        @retrofit2.http.Query("includeSelf") includeSelf: Boolean? = null
    ): SimilarTransactionsResponse
    /**
     * Get categorization clusters
     * Returns an object holding clusters of transactions to be categorized and possible categorization level improvement
     * @param numberOfClusters Max number of clusters returned (optional)
     * @param evaluateEverything (optional)
     */
    @GET("/api/v1/transactions/suggest")
    suspend fun suggest(
        @retrofit2.http.Query("numberOfClusters") numberOfClusters: Int?,
        @retrofit2.http.Query("evaluateEverything") evaluateEverything: Boolean?
    ): SuggestTransactionsResponse
    /**
     * Update transaction Link
     * Updates an transaction part amount and it's counterpart amount.
     * @param id The ID of the transaction to which the part belongs to. (required)
     * @param partId The part ID to update. (required)
     * @param body Object holding the required amount for transaction linking (required)
     */
    @PUT("/api/v1/transactions/{id}/part/{partId}")
    suspend fun updateLink(
        @retrofit2.http.Path("id") id: String,
        @retrofit2.http.Path("partId") partId: String,
        @retrofit2.http.Body body: UpdateTransactionLinkRequest
    ): LinkTransactionsResponse
    /**
     * Update a transaction
     * Updates mutable properties of a list of transactions. The following properties are possible to update:amount, categoryId, date, description.
     * @param id The ID of the transaction (required)
     * @param body The transaction to be updated (required)
     */
    @PUT("/api/v1/transactions/{id}")
    suspend fun updateTransaction(
        @retrofit2.http.Path("id") id: String,
        @retrofit2.http.Body body: Transaction
    ): Response<Unit>
    /**
     * Update a list of transactions
     * Updates mutable properties of a list of transactions. The following properties are possible to update:amount, categoryId, date, description.
     * @param body The transactions to be updated (required)
     */
    @PUT("/api/v1/transactions")
    suspend fun updateTransactions(
        @retrofit2.http.Body body: List<Transaction>
    ): Response<Unit>
}
