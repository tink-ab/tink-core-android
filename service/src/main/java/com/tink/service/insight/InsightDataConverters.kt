package com.tink.service.insight

import com.tink.model.budget.Budget
import com.tink.model.insights.InsightData
import com.tink.model.misc.Amount
import com.tink.model.misc.ExactNumber
import com.tink.model.relations.AmountByCategory
import com.tink.model.relations.ExpensesByDay
import com.tink.model.time.YearMonth
import com.tink.model.time.YearWeek
import com.tink.rest.models.insightdata.AmountWithCurrencyCode
import com.tink.rest.models.insightdata.BudgetPeriod
import com.tink.rest.models.insightdata.ExpenseByCategoryCode
import com.tink.rest.models.insightdata.ExpenseStatisticsByDay
import com.tink.rest.models.insightdata.Month
import com.tink.rest.models.insightdata.Week
import com.tink.service.misc.toInstant
import org.threeten.bp.LocalDate
import com.tink.rest.models.InsightData as InsightDataDto
import com.tink.rest.models.insightdata.BudgetIdToPeriod as BudgetIdToPeriodDto

internal fun InsightDataDto.toCoreModel(): InsightData =
    when (this) {

        is InsightDataDto.AccountBalanceLowData -> InsightData.AccountBalanceLowData(
            accountId,
            balance.toAmount()
        )
        is InsightDataDto.BudgetOverspentData -> InsightData.BudgetResultData(
            budgetId,
            budgetPeriod.toCoreModel()
        )
        is InsightDataDto.BudgetCloseNegativeData -> InsightData.BudgetCloseData(
            budgetId,
            budgetPeriod.toCoreModel(),
            currentTime.toInstant()
        )
        is InsightDataDto.BudgetClosePositiveData -> InsightData.BudgetCloseData(
            budgetId,
            budgetPeriod.toCoreModel(),
            currentTime.toInstant()
        )
        is InsightDataDto.BudgetSuccessData -> InsightData.BudgetResultData(
            budgetId,
            budgetPeriod.toCoreModel()
        )
        is InsightDataDto.BudgetSummaryAchievedData -> InsightData.BudgetSummaryAchievedData(
            achievedBudgets = achievedBudgets.map { it.toCoreModel() },
            overspentBudgets = overspentBudgets.map { it.toCoreModel() },
            savedAmount = savedAmount.toAmount()
        )
        is InsightDataDto.BudgetSummaryOverspentData -> InsightData.BudgetSummaryOverspentData(
            achievedBudgets = achievedBudgets.map { it.toCoreModel() },
            overspentBudgets = overspentBudgets.map { it.toCoreModel() },
            overspentAmount = overspentAmount.toAmount()
        )
        is InsightDataDto.LargeExpenseData -> InsightData.LargeExpenseData(transactionId)
        is InsightDataDto.SingleUncategorizedTransactionData -> InsightData.UncategorizedTransactionData(
            transactionId
        )
        is InsightDataDto.DoubleChargeData -> InsightData.DoubleChargeData(transactionIds)
        is InsightDataDto.WeeklyUncategorizedTransactionsData -> InsightData.WeeklyUncategorizedTransactionsData(
            week.toYearWeek(),
            transactionIds
        )
        is InsightDataDto.WeeklySummaryExpensesByCategoryData -> InsightData.WeeklyExpensesByCategoryData(
            week.toYearWeek(),
            expensesByCategory.map { it.toAmountByCategory() }
        )
        is InsightDataDto.WeeklySummaryExpensesByDayData -> InsightData.WeeklyExpensesByDayData(
            week.toYearWeek(),
            expenseStatisticsByDay.map { it.toExpensesByDay() }
        )
        is InsightDataDto.MonthlySummaryExpensesByCategoryData -> InsightData.MonthlySummaryExpensesByCategoryData(
            month.toYearMonth(),
            expensesByCategory.map { it.toAmountByCategory() }
        )
        is InsightDataDto.LeftToSpendNegativeData,
        InsightDataDto.Unknown -> InsightData.NoData
    }

internal fun AmountWithCurrencyCode.toAmount() = Amount(ExactNumber(amount), currencyCode)

internal fun BudgetPeriod.toCoreModel() =
    Budget.Period(
        start.toInstant(),
        end.toInstant(),
        spentAmount.toAmount(),
        budgetAmount.toAmount()
    )

internal fun BudgetIdToPeriodDto.toCoreModel() =
    InsightData.BudgetIdToPeriod(budgetId, budgetPeriod.toCoreModel())

internal fun Week.toYearWeek() = YearWeek(year, week)
internal fun Month.toYearMonth(): YearMonth = YearMonth(year, month)

internal fun ExpenseByCategoryCode.toAmountByCategory() =
    AmountByCategory(categoryCode, spentAmount.toAmount())

internal fun ExpenseStatisticsByDay.toExpensesByDay() =
    ExpensesByDay(
        LocalDate.of(date[0], date[1], date[2]),
        this.expenseStatistics.totalAmount.toAmount(),
        expenseStatistics.averageAmount.toAmount()
    )
