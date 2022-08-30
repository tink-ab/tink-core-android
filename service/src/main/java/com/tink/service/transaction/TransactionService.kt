package com.tink.service.transaction

import com.tink.model.time.Period
import com.tink.model.transaction.Transaction
import com.tink.rest.apis.SearchApi
import com.tink.rest.apis.TransactionApi
import com.tink.rest.models.CategorizeTransactionsListRequest
import com.tink.rest.models.CategorizeTransactionsRequest
import com.tink.rest.models.SearchQuery
import retrofit2.Response
import javax.inject.Inject

interface TransactionService {
    suspend fun listTransactions(
        accountId: String? = null,
        categoryId: String? = null,
        period: Period? = null,
        offset: Int = 0
    ): List<Transaction>

    suspend fun getTransaction(transactionId: String): Transaction

    suspend fun categorizeTransactions(
        transactionIds: List<String>,
        newCategoryId: String
    ) // TODO: Should return transactions?

    suspend fun getSimilarTransactions(transactionId: String): List<Transaction>

    suspend fun updateTransaction(transaction: Transaction): Response<Unit>
}

internal class TransactionServiceImpl @Inject constructor(
    private val searchApi: SearchApi,
    private val transactionApi: TransactionApi
) : TransactionService {
    override suspend fun listTransactions(
        accountId: String?,
        categoryId: String?,
        period: Period?,
        offset: Int
    ): List<Transaction> {

        fun String.asList(): List<String> = listOf(this)

        val query = SearchQuery(
            accounts = accountId?.asList(),
            categories = categoryId?.asList(),
            offset = offset,
            sort = SearchQuery.SortEnum.DATE,
            includeUpcoming = true
        )

        period?.let {
            query.startDate = it.start.toEpochMilli()
            query.endDate = it.end.toEpochMilli()
        }

        return searchApi.searchQuery(query).results.mapNotNull { it.transaction?.toCoreModel() }
    }

    override suspend fun getTransaction(transactionId: String) =
        transactionApi.getTransaction(transactionId).toCoreModel()

    override suspend fun categorizeTransactions(
        transactionIds: List<String>,
        newCategoryId: String
    ) {
        val request = CategorizeTransactionsListRequest(
            listOf(
                CategorizeTransactionsRequest(newCategoryId, transactionIds)
            )
        )
        transactionApi.categorize(request)
    }

    override suspend fun getSimilarTransactions(transactionId: String) =
        transactionApi.similar(transactionId).transactions.map { it.toCoreModel() }

    override suspend fun updateTransaction(transaction: Transaction) =
        transactionApi.updateTransaction(transaction.id, transaction.toTransactionUpdateObject())
}
