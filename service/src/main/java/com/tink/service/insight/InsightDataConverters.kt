package com.tink.service.insight

import com.tink.model.account.AccountWithName
import com.tink.model.budget.Budget
import com.tink.model.budget.BudgetFilter
import com.tink.model.credentials.RefreshCredential
import com.tink.model.insights.InsightData
import com.tink.model.misc.Amount
import com.tink.model.misc.ExactNumber
import com.tink.model.provider.RefreshProvider
import com.tink.model.relations.AmountByCategory
import com.tink.model.relations.ExpensesByDay
import com.tink.model.time.YearMonth
import com.tink.model.time.YearWeek
import com.tink.rest.models.RecurringPeriodicity
import com.tink.rest.models.insightdata.AmountWithCurrencyCode
import com.tink.rest.models.insightdata.BudgetPeriod
import com.tink.rest.models.insightdata.CommonTransactionsOverview
import com.tink.rest.models.insightdata.ExpenseByCategoryCode
import com.tink.rest.models.insightdata.ExpenseStatisticsByDay
import com.tink.rest.models.insightdata.LargestExpense
import com.tink.rest.models.insightdata.Month
import com.tink.rest.models.insightdata.TransactionSummary
import com.tink.rest.models.insightdata.Week
import com.tink.rest.models.insights.actions.BudgetSuggestion
import com.tink.service.misc.toInstant
import java.time.LocalDate
import com.tink.rest.models.InsightData as InsightDataDto
import com.tink.rest.models.insightdata.AccountWithName as AccountWithNameDto
import com.tink.rest.models.insightdata.BudgetIdToPeriod as BudgetIdToPeriodDto
import com.tink.rest.models.insightdata.RefreshCredential as RefreshCredentialDto
import com.tink.rest.models.insightdata.RefreshProvider as RefreshProviderDto
import com.tink.rest.models.insights.actions.BudgetFilter as BudgetFilterInsightData

internal fun InsightDataDto.toCoreModel(): InsightData =
    when (this) {
        is InsightDataDto.AccountBalanceLowData -> InsightData.AccountBalanceLowData(
            accountId,
            balance.toAmount()
        )
        is InsightDataDto.AggregateRefreshP2d2Credentials -> InsightData.AggregationRefreshPsd2CredentialData(
            credential = credential.toCoreModel()
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
        is InsightDataDto.BudgetSuggestCreateFirstData -> InsightData.BudgetSuggestCreateFirstData
        is InsightDataDto.BudgetSuggestCreateTopCategoryData -> InsightData.BudgetSuggestCreateTopCategoryData(
            categorySpending.toAmountByCategory(),
            suggestedBudgetAmount.toAmount()
        )
        is InsightDataDto.BudgetSuggestCreateTopPrimaryCategoryData -> InsightData.BudgetSuggestCreateTopPrimaryCategoryData(
            categorySpending.toAmountByCategory(),
            suggestedBudgetAmount.toAmount()
        )
        is InsightDataDto.CreditCardLimitCloseData -> InsightData.CreditCardLimitCloseData(
            account.toAccountWithName(),
            availableCredit.toAmount()
        )
        is InsightDataDto.CreditCardLimitReachedData -> InsightData.CreditCardLimitReachedData(
            account.toAccountWithName()
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
        is InsightDataDto.MonthlySummaryExpenseTransactions -> InsightData.MonthlyExpenseTransactionsData(
            month.toYearMonth(),
            transactionSummary.toTransactionSummaryDto()
        )
        is InsightDataDto.WeeklySummaryExpenseTransactions -> InsightData.WeeklyExpenseTransactionsData(
            week.toYearWeek(),
            transactionSummary.toTransactionSummaryDto()
        )
        is InsightDataDto.LeftToSpendNegativeData,
        InsightDataDto.Unknown -> InsightData.NoData
        else -> InsightData.NoData
    }

internal fun TransactionSummary.toTransactionSummaryDto(): com.tink.model.relations.TransactionSummary =
    com.tink.model.relations.TransactionSummary(commonTransactionsOverview.toCommonTransactionsOverviewDto(), totalExpenses.toAmount(), largestExpense.toLargestExpenseDto())

internal fun CommonTransactionsOverview.toCommonTransactionsOverviewDto(): com.tink.model.relations.CommonTransactionsOverview =
    com.tink.model.relations.CommonTransactionsOverview(mostCommonTransactionDescription, totalNumberOfTransactions, mostCommonTransactionCount)

internal fun LargestExpense.toLargestExpenseDto(): com.tink.model.relations.LargestExpense =
    com.tink.model.relations.LargestExpense(date, amount.toAmount(), description, id)

internal fun AmountWithCurrencyCode.toAmount() = Amount(ExactNumber(amount), currencyCode)

internal fun AccountWithNameDto.toAccountWithName() = AccountWithName(accountId, accountName)

internal fun BudgetPeriod.toCoreModel() =
    Budget.Period(
        start.toInstant(),
        end.toInstant(),
        spentAmount.toAmount(),
        budgetAmount.toAmount()
    )

internal fun BudgetIdToPeriodDto.toCoreModel() =
    InsightData.BudgetIdToPeriod(budgetId, budgetPeriod.toCoreModel())

internal fun RefreshCredentialDto.toCoreModel() =
    RefreshCredential(
        id = id,
        provider = provider.toCoreModel(),
        sessionExpiryDate = sessionExpiryDate.toInstant()
    )

internal fun RefreshProviderDto.toCoreModel() =
    RefreshProvider(
        name = name,
        displayName = displayName
    )

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

internal fun BudgetSuggestion.periodicityIfAvailable(): Budget.Periodicity? =
    periodicityType
        ?.let { periodicityType ->
            when (periodicityType) {
                BudgetSuggestion.BudgetPeriodicityTypeEnum.BUDGET_PERIODICITY_TYPE_ONE_OFF -> {
                    oneOffPeriodicityData
                        ?.let { oneOffPeriodicity ->
                            Budget.Periodicity.OneOff(
                                oneOffPeriodicity.start.toInstant(),
                                oneOffPeriodicity.end.toInstant()
                            )
                        }
                }
                BudgetSuggestion.BudgetPeriodicityTypeEnum.BUDGET_PERIODICITY_TYPE_RECURRING -> {
                    recurringPeriodicityData
                        ?.let { recurringPeriodicity ->
                            Budget.Periodicity.Recurring(
                                unit = when (recurringPeriodicity.periodUnit) {
                                    RecurringPeriodicity.PeriodUnitEnum.WEEK -> Budget.Periodicity.Recurring.PeriodUnit.WEEK
                                    RecurringPeriodicity.PeriodUnitEnum.MONTH -> Budget.Periodicity.Recurring.PeriodUnit.MONTH
                                    RecurringPeriodicity.PeriodUnitEnum.YEAR -> Budget.Periodicity.Recurring.PeriodUnit.YEAR
                                    else -> Budget.Periodicity.Recurring.PeriodUnit.UNKNOWN
                                }
                            )
                        }
                }
            }
        }

internal fun BudgetFilterInsightData.toBudgetFilter(): BudgetFilter =
    BudgetFilter(
        accounts = accounts?.map { Budget.Specification.Filter.Account(it) } ?: listOf(),
        categories = categories?.map { Budget.Specification.Filter.Category(it) } ?: listOf(),
        tags = listOf(),
        freeTextQuery = ""
    )
