package com.tink.service.transaction

import com.tink.model.transaction.CategoryType
import com.tink.model.transaction.Transaction
import com.tink.rest.models.TransactionResponse
import com.tink.rest.models.TransactionUpdateObject
import com.tink.service.misc.toAmount
import com.tink.service.misc.toInstant
import com.tink.rest.models.Transaction as TransactionDto

fun TransactionDto.toCoreModel() =
    Transaction(
        accountId = accountId,
        id = id,
        amount = currencyDenominatedAmount!!.toAmount(),
        description = description,
        categoryId = categoryId,
        date = date.toInstant(),
        notes = notes ?: "",
        tags = listOf(),
        upcoming = upcoming,
        pending = pending,
        originalDate = originalDate.toInstant(),
        originalDescription = originalDescription,
        originalAmount = currencyDenominatedOriginalAmount!!.toAmount(),
        inserted = timestamp.toInstant(),
        categoryType = categoryType.toCategoryType()
    )

fun TransactionResponse.toCoreModel() =
    Transaction(
        accountId = accountId,
        id = id,
        amount = currencyDenominatedAmount!!.toAmount(),
        description = description,
        categoryId = categoryId,
        date = date.toInstant(),
        notes = notes.orEmpty(),
        tags = listOf(),
        upcoming = upcoming,
        pending = pending,
        originalDate = originalDate.toInstant(),
        originalDescription = originalDescription,
        originalAmount = currencyDenominatedOriginalAmount!!.toAmount(),
        inserted = timestamp.toInstant(),
        categoryType = categoryType.toCategoryType()
    )

fun Transaction.toTransactionUpdateObject() = TransactionUpdateObject(
    description = description,
    date = date.toEpochMilli(),
    amount = amount.value.unscaledValue.toDouble(),
    notes = notes
)

private fun com.tink.rest.models.Transaction.CategoryTypeEnum.toCategoryType(): CategoryType =
    when (this) {
        com.tink.rest.models.Transaction.CategoryTypeEnum.INCOME -> CategoryType.INCOME
        com.tink.rest.models.Transaction.CategoryTypeEnum.EXPENSES -> CategoryType.EXPENSES
        com.tink.rest.models.Transaction.CategoryTypeEnum.TRANSFERS -> CategoryType.TRANSFERS
    }

private fun TransactionResponse.CategoryTypeEnum.toCategoryType(): CategoryType =
    when (this) {
        TransactionResponse.CategoryTypeEnum.INCOME -> CategoryType.INCOME
        TransactionResponse.CategoryTypeEnum.EXPENSES -> CategoryType.EXPENSES
        TransactionResponse.CategoryTypeEnum.TRANSFERS -> CategoryType.TRANSFERS
    }
