package com.tink.service.transaction

import com.tink.model.time.Period
import com.tink.model.transaction.Transaction
import com.tink.service.handler.ResultHandler
import com.tink.service.observer.ChangeObserver
import javax.inject.Inject

interface TransactionService {
    fun listAndSubscribeForAccountId(
        accountId: String,
        listener: ChangeObserver<List<Transaction>>
    ): Pageable

    fun listAndSubscribeForCategoryCode(
        categoryCode: String,
        listener: ChangeObserver<List<Transaction>>
    ): Pageable

    fun listAndSubscribeForCategoryCodeAndPeriod(
        categoryCode: String,
        period: Period,
        listener: ChangeObserver<List<Transaction>>
    ): Pageable

    fun listAllAndSubscribeForCategoryCodeAndPeriod(
        categoryCode: String,
        period: Period,
        listener: ChangeObserver<List<Transaction>>
    )

    fun listAndSubscribeForLeftToSpendAndPeriod(
        period: Period,
        listener: ChangeObserver<List<Transaction>>
    ): Pageable

    fun listAndSubscribeForLatestTransactions(
        includeUpcoming: Boolean,
        listener: ChangeObserver<List<Transaction>>
    ): Pageable

    fun listAndSubscribeForLatestTransactions(
        includeUpcoming: Boolean,
        pageSize: Int,
        listener: ChangeObserver<List<Transaction>>
    ): Pageable

    fun unsubscribe(listener: ChangeObserver<List<Transaction>>)

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

    fun subscribe(changeObserver: ChangeObserver<List<Transaction>>)
}

class TransactionServiceImpl @Inject constructor() : TransactionService {
    override fun listAndSubscribeForAccountId(
        accountId: String,
        listener: ChangeObserver<List<Transaction>>
    ): Pageable {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun listAndSubscribeForCategoryCode(
        categoryCode: String,
        listener: ChangeObserver<List<Transaction>>
    ): Pageable {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun listAndSubscribeForCategoryCodeAndPeriod(
        categoryCode: String,
        period: Period,
        listener: ChangeObserver<List<Transaction>>
    ): Pageable {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun listAllAndSubscribeForCategoryCodeAndPeriod(
        categoryCode: String,
        period: Period,
        listener: ChangeObserver<List<Transaction>>
    ) {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun listAndSubscribeForLeftToSpendAndPeriod(
        period: Period,
        listener: ChangeObserver<List<Transaction>>
    ): Pageable {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun listAndSubscribeForLatestTransactions(
        includeUpcoming: Boolean,
        listener: ChangeObserver<List<Transaction>>
    ): Pageable {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun listAndSubscribeForLatestTransactions(
        includeUpcoming: Boolean,
        pageSize: Int,
        listener: ChangeObserver<List<Transaction>>
    ): Pageable {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun unsubscribe(listener: ChangeObserver<List<Transaction>>) {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun updateTransaction(
        descriptor: TransactionUpdateDescriptor,
        handler: ResultHandler<Transaction>
    ) {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun getTransaction(transactionId: String, handler: ResultHandler<Transaction>) {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun categorizeTransactions(
        transactionIds: List<String>,
        categoryCode: String,
        handler: ResultHandler<List<Transaction>>
    ) {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun getSimilarTransactions(
        transactionId: String,
        handler: ResultHandler<List<Transaction>>
    ) {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun subscribe(changeObserver: ChangeObserver<List<Transaction>>) {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }
}
