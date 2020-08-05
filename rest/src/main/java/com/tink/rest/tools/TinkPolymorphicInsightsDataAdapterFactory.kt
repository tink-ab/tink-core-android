package com.tink.rest.tools

import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import com.tink.rest.models.InsightData
import com.tink.rest.models.InsightData.TypeEnum

object TinkPolymorphicInsightsDataAdapterFactory {

    fun create(): PolymorphicJsonAdapterFactory<InsightData> =
        PolymorphicJsonAdapterFactory
            .of(InsightData::class.java, "type")
            .withDefaultValue(InsightData.Unknown)
            .withSubtype(
                InsightData.AccountBalanceLowData::class.java,
                TypeEnum.ACCOUNT_BALANCE_LOW.value
            )
            .withSubtype(
                InsightData.BudgetOverspentData::class.java,
                TypeEnum.BUDGET_OVERSPENT.value
            )
            .withSubtype(
                InsightData.BudgetCloseNegativeData::class.java,
                TypeEnum.BUDGET_CLOSE_NEGATIVE.value
            )
            .withSubtype(
                InsightData.BudgetClosePositiveData::class.java,
                TypeEnum.BUDGET_CLOSE_POSITIVE.value
            )
            .withSubtype(
                InsightData.BudgetSuccessData::class.java,
                TypeEnum.BUDGET_SUCCESS.value
            )
            .withSubtype(
                InsightData.BudgetSummaryAchievedData::class.java,
                TypeEnum.BUDGET_SUMMARY_ACHIEVED.value
            )
            .withSubtype(
                InsightData.BudgetSummaryOverspentData::class.java,
                TypeEnum.BUDGET_SUMMARY_OVERSPENT.value
            )
            .withSubtype(
                InsightData.LargeExpenseData::class.java,
                TypeEnum.LARGE_EXPENSE.value
            )
            .withSubtype(
                InsightData.SingleUncategorizedTransactionData::class.java,
                TypeEnum.SINGLE_UNCATEGORIZED_TRANSACTION.value
            )
            .withSubtype(
                InsightData.DoubleChargeData::class.java,
                TypeEnum.DOUBLE_CHARGE.value
            )
            .withSubtype(
                InsightData.WeeklyUncategorizedTransactionsData::class.java,
                TypeEnum.WEEKLY_UNCATEGORIZED_TRANSACTIONS.value
            )
            .withSubtype(
                InsightData.WeeklySummaryExpensesByCategoryData::class.java,
                TypeEnum.WEEKLY_SUMMARY_EXPENSES_BY_CATEGORY.value
            )
            .withSubtype(
                InsightData.WeeklySummaryExpensesByDayData::class.java,
                TypeEnum.WEEKLY_SUMMARY_EXPENSES_BY_DAY.value
            )
            .withSubtype(
                InsightData.MonthlySummaryExpensesByCategoryData::class.java,
                TypeEnum.MONTHLY_SUMMARY_EXPENSES_BY_CATEGORY.value
            )
            .withSubtype(
                InsightData.LeftToSpendNegativeData::class.java,
                TypeEnum.LEFT_TO_SPEND_NEGATIVE.value
            )
}
