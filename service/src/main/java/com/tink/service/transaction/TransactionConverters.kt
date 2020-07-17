package com.tink.service.transaction

import com.tink.model.transaction.Transaction
import com.tink.rest.models.TransactionResponse
import com.tink.service.misc.toAmount
import com.tink.service.misc.toInstant
import com.tink.rest.models.Transaction as TransactionDto

fun TransactionDto.toCoreModel() =
    Transaction(
        accountId = accountId,
        id = id,
        amount = currencyDenominatedAmount!!.toAmount(),
        dispensableAmount = currencyDenominatedAmount!!.toAmount(), //TODO
        description = description,
        categoryCode = categoryId,
        date = date.toInstant(),
        notes = notes,
        tags = listOf(),
        upcoming = upcoming,
        pending = pending,
        originalDate = originalDate.toInstant(),
        originalDescription = originalDescription,
        originalAmount = currencyDenominatedOriginalAmount!!.toAmount(),
        inserted = 0L.toInstant(), //TODO
        details = null
    )

fun TransactionResponse.toCoreModel() =
    Transaction(
        accountId = accountId,
        id = id,
        amount = currencyDenominatedAmount!!.toAmount(),
        dispensableAmount = currencyDenominatedAmount!!.toAmount(), //TODO
        description = description,
        categoryCode = categoryId,
        date = date.toInstant(),
        notes = notes,
        tags = listOf(),
        upcoming = upcoming,
        pending = pending,
        originalDate = originalDate.toInstant(),
        originalDescription = originalDescription,
        originalAmount = currencyDenominatedOriginalAmount!!.toAmount(),
        inserted = 0L.toInstant(), //TODO
        details = null
    )

