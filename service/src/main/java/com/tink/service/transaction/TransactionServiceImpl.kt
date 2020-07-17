package com.tink.service.transaction

import com.tink.model.time.Period
import com.tink.model.transaction.Transaction
import com.tink.rest.apis.SearchApi
import com.tink.rest.models.SearchQuery
import javax.inject.Inject

interface TransactionService {
    suspend fun listTransactions(
        accountId: String? = null,
        categoryCode: String? = null,
        period: Period? = null
    ): List<Transaction>
}

internal class TransactionServiceImpl @Inject constructor(
    private val api: SearchApi
) : TransactionService {
    override suspend fun listTransactions(
        accountId: String?,
        categoryCode: String?,
        period: Period?
    ): List<Transaction> {

        fun String.asList(): List<String> = listOf(this)

        val query = SearchQuery(
            accounts = accountId?.asList(),
            categories = categoryCode?.asList()
        )

        period?.let {
            query.startDate = it.start.toEpochMilli()
            query.endDate = it.end.toEpochMilli()
        }

        return api.searchQuery(query).results.mapNotNull { it.transaction?.toCoreModel() }
    }
}
