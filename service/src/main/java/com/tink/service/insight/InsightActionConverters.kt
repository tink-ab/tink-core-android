package com.tink.service.insight

import com.tink.model.insights.InsightAction
import com.tink.rest.models.InsightActionData
import com.tink.rest.models.InsightProposedAction
import com.tink.service.misc.toInstant

fun InsightProposedAction.toCoreModel() =
    InsightAction(
        label ?: "",
        data.toCoreModel()
    )

private fun InsightActionData?.toCoreModel(): InsightAction.Data =
    when (this) {
        is InsightActionData.CreateTransferActionData -> InsightAction.Data.CreateTransfer(
            sourceAccount,
            destinationAccount,
            amount.toAmount()
        )
        is InsightActionData.ViewBudgetActionData -> InsightAction.Data.ViewBudget(
            budgetId,
            budgetPeriodStartTime.toInstant()
        )
        is InsightActionData.ViewTransactionsByCategoryActionData ->
            InsightAction.Data.ViewTransactionsByCategory(
                transactionIdsByCategory.map { (key, value) -> key to value.transactionIds }.toMap()
            )
        is InsightActionData.ViewTransactionActionData -> InsightAction.Data.ViewTransactions(
            transactionId
        )
        is InsightActionData.CategorizeExpenseActionData -> InsightAction.Data.CategorizeExpense(
            transactionId
        )
        is InsightActionData.ViewTransactionsActionData -> InsightAction.Data.ViewTransactions(
            transactionIds.map { it.id }
        )
        is InsightActionData.Acknowledge -> InsightAction.Data.Acknowledge
        is InsightActionData.Dismiss -> InsightAction.Data.Dismiss
        is InsightActionData.CategorizeTransactionsActionData,
        InsightActionData.Unknown,
        null -> InsightAction.Data.NoData
    }
