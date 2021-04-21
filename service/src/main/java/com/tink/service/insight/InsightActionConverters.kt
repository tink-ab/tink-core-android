package com.tink.service.insight

import com.tink.model.insights.InsightAction
import com.tink.rest.models.InsightActionData
import com.tink.rest.models.InsightProposedAction
import com.tink.service.misc.toInstant

fun InsightProposedAction.toCoreModel() =
    InsightAction(
        label ?: "",
        data?.type?.convertType() ?: InsightAction.Type.UNKNOWN,
        data.toCoreModel()
    )

private fun InsightActionData?.toCoreModel(): InsightAction.Data =
    when (this) {
        is InsightActionData.CreateTransferActionData -> InsightAction.Data.CreateTransfer(
            sourceAccount,
            destinationAccount,
            amount?.toAmount(),
            sourceAccountNumber,
            destinationAccountNumber
        )
        is InsightActionData.ViewBudgetActionData -> InsightAction.Data.ViewBudget(
            budgetId,
            budgetPeriodStartTime.toInstant()
        )
        is InsightActionData.CreateBudgetActionData -> InsightAction.Data.CreateBudget(
            budgetSuggestion.filter?.toBudgetFilter(),
            budgetSuggestion.amount?.toAmount(),
            budgetSuggestion.periodicityIfAvailable()
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
        is InsightActionData.CategorizeTransactionsActionData -> InsightAction.Data.CategorizeTransactions(
            transactionIds
        )
        is InsightActionData.ViewTransactionsActionData -> InsightAction.Data.ViewTransactions(
            transactionIds.map { it.id }
        )
        is InsightActionData.Acknowledge -> InsightAction.Data.Acknowledge
        is InsightActionData.Dismiss -> InsightAction.Data.Dismiss
        InsightActionData.Unknown,
        null -> InsightAction.Data.NoData
        else -> InsightAction.Data.NoData
    }

private fun InsightActionData.TypeEnum.convertType(): InsightAction.Type =
    when (this) {
        InsightActionData.TypeEnum.ACKNOWLEDGE -> InsightAction.Type.ACKNOWLEDGE
        InsightActionData.TypeEnum.DISMISS -> InsightAction.Type.DISMISS
        InsightActionData.TypeEnum.VIEW_BUDGET -> InsightAction.Type.VIEW_BUDGET
        InsightActionData.TypeEnum.CREATE_BUDGET -> InsightAction.Type.CREATE_BUDGET
        InsightActionData.TypeEnum.CREATE_TRANSFER -> InsightAction.Type.CREATE_TRANSFER
        InsightActionData.TypeEnum.VIEW_TRANSACTION -> InsightAction.Type.VIEW_TRANSACTION
        InsightActionData.TypeEnum.CATEGORIZE_EXPENSE -> InsightAction.Type.CATEGORIZE_EXPENSE
        InsightActionData.TypeEnum.VIEW_TRANSACTIONS -> InsightAction.Type.VIEW_TRANSACTIONS
        InsightActionData.TypeEnum.CATEGORIZE_TRANSACTIONS -> InsightAction.Type.CATEGORIZE_TRANSACTIONS
        InsightActionData.TypeEnum.VIEW_TRANSACTIONS_BY_CATEGORY -> InsightAction.Type.VIEW_TRANSACTIONS_BY_CATEGORY
        InsightActionData.TypeEnum.UNKNOWN -> InsightAction.Type.UNKNOWN
    }
