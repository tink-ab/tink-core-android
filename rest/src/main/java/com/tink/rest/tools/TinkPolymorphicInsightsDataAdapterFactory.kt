package com.tink.rest.tools

import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import com.tink.rest.models.InsightData
import com.tink.rest.models.InsightData.TypeEnum

object TinkPolymorphicInsightsDataAdapterFactory {

    fun create(): PolymorphicJsonAdapterFactory<InsightData> =
        PolymorphicJsonAdapterFactory.of(InsightData::class.java, "type")
            .withSubtype(
                InsightData.AccountBalanceLow::class.java,
                TypeEnum.ACCOUNT_BALANCE_LOW.value
            )
            .withSubtype(
                InsightData.BudgetOverspent::class.java,
                TypeEnum.BUDGET_OVERSPENT.value
            )
            .withSubtype(
                InsightData.BudgetCloseNegative::class.java,
                TypeEnum.BUDGET_CLOSE_NEGATIVE.value
            )
            .withSubtype(
                InsightData.BudgetClosePositive::class.java,
                TypeEnum.BUDGET_CLOSE_POSITIVE.value
            )
            .withSubtype(
                InsightData.BudgetSuccess::class.java,
                TypeEnum.BUDGET_SUCCESS.value
            )
            .withSubtype(
                InsightData.BudgetSummaryAchieved::class.java,
                TypeEnum.BUDGET_SUMMARY_ACHIEVED.value
            )
            .withSubtype(
                InsightData.BudgetSummaryOverspent::class.java,
                TypeEnum.BUDGET_SUMMARY_OVERSPENT.value
            )
            .withSubtype(
                InsightData.LargeExpense::class.java,
                TypeEnum.LARGE_EXPENSE.value
            )
            .withSubtype(
                InsightData.SingleUncategorizedTransaction::class.java,
                TypeEnum.SINGLE_UNCATEGORIZED_TRANSACTION.value
            )
            .withSubtype(
                InsightData.DoubleCharge::class.java,
                TypeEnum.DOUBLE_CHARGE.value
            )
            .withSubtype(
                InsightData.WeeklyUncategorizedTransactions::class.java,
                TypeEnum.WEEKLY_UNCATEGORIZED_TRANSACTIONS.value
            )
            .withSubtype(
                InsightData.WeeklySummaryExpensesByCategory::class.java,
                TypeEnum.WEEKLY_SUMMARY_EXPENSES_BY_CATEGORY.value
            )
            .withSubtype(
                InsightData.WeeklySummaryExpensesByDay::class.java,
                TypeEnum.WEEKLY_SUMMARY_EXPENSES_BY_DAY.value
            )
            .withSubtype(
                InsightData.MonthlySummaryExpensesByCategory::class.java,
                TypeEnum.MONTHLY_SUMMARY_EXPENSES_BY_CATEGORY.value
            )
            .withSubtype(
                InsightData.LeftToSpendNegative::class.java,
                TypeEnum.LEFT_TO_SPEND_NEGATIVE.value
            )
            .withSubtype(
                InsightData.Unknown::class.java,
                TypeEnum.UNKNOWN.value
            )

}
