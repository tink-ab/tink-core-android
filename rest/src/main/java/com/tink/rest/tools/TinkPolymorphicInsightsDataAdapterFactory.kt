package com.tink.rest.tools

import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import com.tink.rest.models.InsightActionData
import com.tink.rest.models.InsightData
import com.tink.rest.models.InsightData.TypeEnum as DataType
import com.tink.rest.models.InsightActionData.TypeEnum as ActionType

object TinkPolymorphicInsightsDataAdapterFactory {

    fun createInsightDataFactory(): PolymorphicJsonAdapterFactory<InsightData> =
        PolymorphicJsonAdapterFactory
            .of(InsightData::class.java, "type")
            .withDefaultValue(InsightData.Unknown)
            .withSubtype(
                InsightData.AccountBalanceLowData::class.java,
                DataType.ACCOUNT_BALANCE_LOW.value
            )
            .withSubtype(
                InsightData.BudgetOverspentData::class.java,
                DataType.BUDGET_OVERSPENT.value
            )
            .withSubtype(
                InsightData.BudgetCloseNegativeData::class.java,
                DataType.BUDGET_CLOSE_NEGATIVE.value
            )
            .withSubtype(
                InsightData.BudgetClosePositiveData::class.java,
                DataType.BUDGET_CLOSE_POSITIVE.value
            )
            .withSubtype(
                InsightData.BudgetSuccessData::class.java,
                DataType.BUDGET_SUCCESS.value
            )
            .withSubtype(
                InsightData.BudgetSummaryAchievedData::class.java,
                DataType.BUDGET_SUMMARY_ACHIEVED.value
            )
            .withSubtype(
                InsightData.BudgetSummaryOverspentData::class.java,
                DataType.BUDGET_SUMMARY_OVERSPENT.value
            )
            .withSubtype(
                InsightData.LargeExpenseData::class.java,
                DataType.LARGE_EXPENSE.value
            )
            .withSubtype(
                InsightData.SingleUncategorizedTransactionData::class.java,
                DataType.SINGLE_UNCATEGORIZED_TRANSACTION.value
            )
            .withSubtype(
                InsightData.DoubleChargeData::class.java,
                DataType.DOUBLE_CHARGE.value
            )
            .withSubtype(
                InsightData.WeeklyUncategorizedTransactionsData::class.java,
                DataType.WEEKLY_UNCATEGORIZED_TRANSACTIONS.value
            )
            .withSubtype(
                InsightData.WeeklySummaryExpensesByCategoryData::class.java,
                DataType.WEEKLY_SUMMARY_EXPENSES_BY_CATEGORY.value
            )
            .withSubtype(
                InsightData.WeeklySummaryExpensesByDayData::class.java,
                DataType.WEEKLY_SUMMARY_EXPENSES_BY_DAY.value
            )
            .withSubtype(
                InsightData.MonthlySummaryExpensesByCategoryData::class.java,
                DataType.MONTHLY_SUMMARY_EXPENSES_BY_CATEGORY.value
            )
            .withSubtype(
                InsightData.LeftToSpendNegativeData::class.java,
                DataType.LEFT_TO_SPEND_NEGATIVE.value
            )

    fun createActionDataFactory(): PolymorphicJsonAdapterFactory<InsightActionData> =
        PolymorphicJsonAdapterFactory
            .of(InsightActionData::class.java, "type")
            .withDefaultValue(InsightActionData.Unknown)
            .withSubtype(
                InsightActionData.Acknowledge::class.java,
                ActionType.ACKNOWLEDGE.value
            )
            .withSubtype(
                InsightActionData.Dismiss::class.java,
                ActionType.DISMISS.value
            )
            .withSubtype(
                InsightActionData.ViewBudgetActionData::class.java,
                ActionType.VIEW_BUDGET.value
            )
            .withSubtype(
                InsightActionData.CreateTransferActionData::class.java,
                ActionType.CREATE_TRANSFER.value
            )
            .withSubtype(
                InsightActionData.ViewTransactionActionData::class.java,
                ActionType.VIEW_TRANSACTION.value
            )
            .withSubtype(
                InsightActionData.CategorizeExpenseActionData::class.java,
                ActionType.CATEGORIZE_EXPENSE.value
            )
            .withSubtype(
                InsightActionData.ViewTransactionsActionData::class.java,
                ActionType.VIEW_TRANSACTIONS.value
            )
            .withSubtype(
                InsightActionData.CategorizeTransactionsActionData::class.java,
                ActionType.CATEGORIZE_TRANSACTIONS.value
            )
            .withSubtype(
                InsightActionData.ViewTransactionsByCategoryActionData::class.java,
                ActionType.VIEW_TRANSACTIONS_BY_CATEGORY.value
            )


}
