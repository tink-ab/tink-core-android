package com.tink.service.transaction

import com.tink.model.time.Period
import com.tink.model.transaction.Transaction
import com.tink.service.handler.ResultHandler
import com.tink.service.observer.ChangeObserver

interface TransactionService {
    fun listAndSubscribeForAccountId(
        accountId: String?,
        listener: ChangeObserver<Transaction?>?
    ): Pageable

    fun listAndSubscribeForCategoryCode(
        categoryCode: String?,
        listener: ChangeObserver<Transaction?>?
    ): Pageable

    fun listAndSubscribeForCategoryCodeAndPeriod(
        categoryCode: String?,
        period: Period?, listener: ChangeObserver<Transaction?>?
    ): Pageable

    fun listAllAndSubscribeForCategoryCodeAndPeriod(
        categoryCode: String?,
        period: Period?,
        listener: ChangeObserver<Transaction?>?
    )

    fun listAndSubscribeForLeftToSpendAndPeriod(
        period: Period?,
        listener: ChangeObserver<Transaction?>?
    ): Pageable

    fun search(
        query: String?, listener: ChangeObserver<Transaction?>?,
        metadataResultHandler: ResultHandler<SearchResultMetadata?>?
    ): Pageable

    fun listAndSubscribeForLatestTransactions(
        includeUpcomming: Boolean,
        listener: ChangeObserver<Transaction?>?
    ): Pageable

    fun listAndSubscribeForLatestTransactions(
        includeUpcomming: Boolean,
        pageSize: Int, listener: ChangeObserver<Transaction?>?
    ): Pageable

    fun unsubscribe(listener: ChangeObserver<Transaction?>?)

    fun updateTransaction(
        transaction: Transaction?,
        handler: ResultHandler<Transaction?>?
    )

    fun getTransaction(
        transactionId: String?,
        handler: ResultHandler<Transaction?>?
    )

    fun categorizeTransactions(
        transactionIds: List<String?>?, categoryCode: String?,
        handler: ResultHandler<List<Transaction?>?>?
    )

    fun getSimilarTransactions(
        transactionId: String?,
        handler: ResultHandler<List<Transaction?>?>?
    )

    fun subscribe(changeObserver: ChangeObserver<Transaction?>?)
}

