package com.tink.service.transaction

import com.tink.model.time.Period
import com.tink.model.transaction.Transaction
import com.tink.service.handler.ResultHandler
import com.tink.service.observer.ListChangeObserver

interface TransactionService {
    fun listAndSubscribeForAccountId(
        accountId: String,
        listener: ListChangeObserver<Transaction>
    ): Pageable

    fun listAndSubscribeForCategoryCode(
        categoryCode: String,
        listener: ListChangeObserver<Transaction>
    ): Pageable

    fun listAndSubscribeForCategoryCodeAndPeriod(
        categoryCode: String,
        period: Period,
        listener: ListChangeObserver<Transaction>
    ): Pageable

    fun listAllAndSubscribeForCategoryCodeAndPeriod(
        categoryCode: String,
        period: Period,
        listener: ListChangeObserver<Transaction>
    )

    fun listAndSubscribeForLeftToSpendAndPeriod(
        period: Period,
        listener: ListChangeObserver<Transaction>
    ): Pageable

    fun listAndSubscribeForLatestTransactions(
        includeUpcoming: Boolean,
        listener: ListChangeObserver<Transaction>
    ): Pageable

    fun listAndSubscribeForLatestTransactions(
        includeUpcoming: Boolean,
        pageSize: Int,
        listener: ListChangeObserver<Transaction>
    ): Pageable

    fun unsubscribe(listener: ListChangeObserver<Transaction>)

    fun updateTransaction(
        descriptor: TransactionUpdateDescriptor,
        handler: ResultHandler<Transaction>
    )

    fun getTransaction(
        transactionId: String,
        handler: ResultHandler<Transaction>
    )

    fun categorizeTransactions(
        transactionIds: List<String>,
        categoryCode: String,
        handler: ResultHandler<List<Transaction>>
    )

    fun getSimilarTransactions(
        transactionId: String,
        handler: ResultHandler<List<Transaction>>
    )

    fun subscribe(changeObserver: ListChangeObserver<Transaction>)
}

