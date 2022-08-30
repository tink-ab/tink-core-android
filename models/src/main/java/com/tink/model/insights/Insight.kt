package com.tink.model.insights

import android.os.Parcelable
import com.tink.model.account.AccountWithName
import com.tink.model.budget.Budget
import com.tink.model.budget.BudgetFilter
import com.tink.model.budget.BudgetPeriodicity
import com.tink.model.credentials.RefreshCredential
import com.tink.model.leftToSpend.LeftToSpendStatisticsMid
import com.tink.model.misc.Amount
import com.tink.model.relations.AmountByCategory
import com.tink.model.relations.ExpensesByDay
import com.tink.model.relations.TransactionSummary
import com.tink.model.time.YearMonth
import com.tink.model.time.YearWeek
import kotlinx.android.parcel.Parcelize
import java.time.Instant

@Parcelize
data class Insight(
    val id: String,
    val type: InsightType,
    val title: String,
    val description: String,
    val created: Instant,
    val actions: List<InsightAction>,
    val state: InsightState,
    val data: InsightData,
    var viewDetails: ViewDetails? = null
) : Parcelable {
    /**
     * All subclasses should be data classes or provide a meaningful `equals()` function
     */
    interface ViewDetails : Parcelable
}

@Parcelize
data class InsightAction(
    val label: String,
    val actionType: Type,
    val data: Data
) : Parcelable {

    /**
     * All subclasses should be data classes or provide a meaningful `equals()` function
     */
    sealed class Data : Parcelable {

        @Parcelize
        object NoData : Data()

        @Parcelize
        object Acknowledge : Data()

        @Parcelize
        object Dismiss : Data()

        @Parcelize
        data class ViewBudget(val budgetId: String, val periodStartDate: Instant) : Data()

        @Parcelize
        data class CreateBudget(
            val budgetFilter: BudgetFilter?,
            val amount: Amount?,
            val periodicity: BudgetPeriodicity?
        ) : Data()

        @Parcelize
        data class CreateTransfer(
            val sourceUri: String? = null,
            val destinationUri: String? = null,
            val amount: Amount? = null,
            val sourceAccountNumber: String? = null,
            val destinationAccountNumber: String? = null
        ) : Data()

        @Parcelize
        data class CategorizeExpense(val transactionId: String) : Data()

        @Parcelize
        data class CategorizeTransactions(val transactionIds: List<String>) : Data()

        @Parcelize
        data class RefreshCredential(val credentialId: String) : Data()

        @Parcelize
        data class ViewAccount(val accountId: String) : Data()

        @Parcelize
        data class ViewTransactions(val transactionIds: List<String>) : Data() {
            constructor(transactionId: String) : this(listOf(transactionId))
        }

        @Parcelize
        data class ViewTransactionsByCategory(
            val transactionsByCategory: Map<String, List<String>>
        ) : Data()

        @Parcelize
        data class ViewLeftToSpend(val month: YearMonth) : Data()
    }

    enum class Type(val value: String) {
        ACKNOWLEDGE("ACKNOWLEDGE"),
        CATEGORIZE_EXPENSE("CATEGORIZE_EXPENSE"),
        CATEGORIZE_TRANSACTIONS("CATEGORIZE_TRANSACTIONS"),
        CREATE_BUDGET("CREATE_BUDGET"),
        CREATE_TRANSFER("CREATE_TRANSFER"),
        DISMISS("DISMISS"),
        UNKNOWN("UNKNOWN"),
        VIEW_ACCOUNT("VIEW_ACCOUNT"),
        VIEW_BUDGET("VIEW_BUDGET"),
        VIEW_TRANSACTION("VIEW_TRANSACTION"),
        VIEW_TRANSACTIONS("VIEW_TRANSACTIONS"),
        VIEW_TRANSACTIONS_BY_CATEGORY("VIEW_TRANSACTIONS_BY_CATEGORY"),
        REFRESH_CREDENTIAL("REFRESH_CREDENTIAL"),
        VIEW_LEFT_TO_SPEND("VIEW_LEFT_TO_SPEND")
    }
}

enum class InsightType {
    ACCOUNT_BALANCE_LOW,
    AGGREGATION_REFRESH_PSD2_CREDENTIAL,
    BUDGET_CLOSE_NEGATIVE,
    BUDGET_CLOSE_POSITIVE,
    BUDGET_OVERSPENT,
    BUDGET_SUCCESS,
    BUDGET_SUGGEST_CREATE_FIRST,
    BUDGET_SUGGEST_CREATE_TOP_CATEGORY,
    BUDGET_SUGGEST_CREATE_TOP_PRIMARY_CATEGORY,
    BUDGET_SUMMARY_ACHIEVED,
    BUDGET_SUMMARY_OVERSPENT,
    CREDIT_CARD_LIMIT_CLOSE,
    CREDIT_CARD_LIMIT_REACHED,
    DOUBLE_CHARGE,
    LARGE_EXPENSE,
    LEFT_TO_SPEND_NEGATIVE,
    LEFT_TO_SPEND_NEGATIVE_BEGINNING_MONTH,
    LEFT_TO_SPEND_NEGATIVE_MID_MONTH,
    LEFT_TO_SPEND_NEGATIVE_SUMMARY,
    LEFT_TO_SPEND_POSITIVE_BEGINNING_MONTH,
    LEFT_TO_SPEND_POSITIVE_FINAL_WEEK,
    LEFT_TO_SPEND_POSITIVE_MID_MONTH,
    LEFT_TO_SPEND_POSITIVE_SUMMARY_SAVINGS_ACCOUNT,
    MONTHLY_SUMMARY_EXPENSES_BY_CATEGORY,
    MONTHLY_SUMMARY_EXPENSE_TRANSACTIONS,
    NEW_INCOME_TRANSACTION,
    SINGLE_UNCATEGORIZED_TRANSACTION,
    SPENDING_BY_CATEGORY_INCREASED,
    SPENDING_BY_PRIMARY_CATEGORY_INCREASED,
    SUGGEST_SET_UP_SAVINGS_ACCOUNT,
    UNKNOWN,
    WEEKLY_SUMMARY_EXPENSES_BY_CATEGORY,
    WEEKLY_SUMMARY_EXPENSES_BY_DAY,
    WEEKLY_SUMMARY_EXPENSE_TRANSACTIONS,
    WEEKLY_UNCATEGORIZED_TRANSACTIONS
}

sealed class InsightState : Parcelable {

    @Parcelize
    data class Archived(val archivedDate: Instant) : InsightState()

    @Parcelize
    object Active : InsightState()
}

/**
 * All subclasses should be data classes or provide a meaningful `equals()` function
 */
sealed class InsightData : Parcelable {

    @Parcelize
    object NoData : InsightData()

    @Parcelize
    data class AccountBalanceLowData(
        val accountId: String,
        val balance: Amount
    ) : InsightData()

    @Parcelize
    data class AggregationRefreshPsd2CredentialData(
        val credential: RefreshCredential
    ) : InsightData()

    @Parcelize
    data class BudgetResultData(
        val budgetId: String,
        val budgetPeriod: Budget.Period
    ) : InsightData()

    @Parcelize
    data class BudgetSummaryAchievedData(
        val achievedBudgets: List<BudgetIdToPeriod>,
        val overspentBudgets: List<BudgetIdToPeriod>,
        val savedAmount: Amount
    ) : InsightData()

    @Parcelize
    data class BudgetSummaryOverspentData(
        val achievedBudgets: List<BudgetIdToPeriod>,
        val overspentBudgets: List<BudgetIdToPeriod>,
        val overspentAmount: Amount
    ) : InsightData()

    @Parcelize
    object BudgetSuggestCreateFirstData : InsightData()

    @Parcelize
    data class BudgetSuggestCreateTopCategoryData(
        val categorySpending: AmountByCategory,
        val suggestedBudgetAmount: Amount
    ) : InsightData()

    @Parcelize
    data class BudgetSuggestCreateTopPrimaryCategoryData(
        val categorySpending: AmountByCategory,
        val suggestedBudgetAmount: Amount
    ) : InsightData()

    @Parcelize
    data class BudgetCloseData(
        val budgetId: String,
        val budgetPeriod: Budget.Period,
        val currentTime: Instant
    ) : InsightData()

    @Parcelize
    data class CreditCardLimitCloseData(
        val account: AccountWithName,
        val availableCredit: Amount
    ) : InsightData()

    @Parcelize
    data class CreditCardLimitReachedData(
        val account: AccountWithName
    ) : InsightData()

    @Parcelize
    data class UncategorizedTransactionData(
        val transactionId: String
    ) : InsightData()

    @Parcelize
    data class LargeExpenseData(
        val transactionId: String
    ) : InsightData()

    @Parcelize
    data class DoubleChargeData(
        val transactionIds: List<String>
    ) : InsightData()

    @Parcelize
    data class WeeklyExpensesByCategoryData(
        val week: YearWeek,
        val expenses: List<AmountByCategory>
    ) : InsightData()

    @Parcelize
    data class WeeklyUncategorizedTransactionsData(
        val week: YearWeek,
        val transactionIds: List<String>
    ) : InsightData()

    @Parcelize
    data class WeeklyExpensesByDayData(
        val week: YearWeek,
        val expensesByDay: List<ExpensesByDay>
    ) : InsightData()

    @Parcelize
    data class MonthlySummaryExpensesByCategoryData(
        val month: YearMonth,
        val expenses: List<AmountByCategory>
    ) : InsightData()

    @Parcelize
    data class MonthlyExpenseTransactionsData(
        val month: YearMonth,
        val transactionSummary: TransactionSummary
    ) : InsightData()

    @Parcelize
    data class WeeklyExpenseTransactionsData(
        val week: YearWeek,
        val transactionSummary: TransactionSummary
    ) : InsightData()

    // Simple data holders

    @Parcelize
    data class BudgetIdToPeriod(
        val budgetId: String,
        val budgetPeriod: Budget.Period
    ) : Parcelable

    @Parcelize
    data class LeftToSpendNegative(
        val createdAt: Long,
        val leftToSpend: Amount,
        val month: YearMonth
    ) : InsightData()

    @Parcelize
    data class LeftToSpendPositiveMidMonth(
        val amountDifference: Amount,
        val leftToSpendStatistics: LeftToSpendStatisticsMid,
        val month: YearMonth
    ) : InsightData()

    @Parcelize
    data class LeftToSpendNegativeMidMonth(
        val amountDifference: Amount,
        val leftToSpendStatistics: LeftToSpendStatisticsMid,
        val month: YearMonth
    ) : InsightData()

    @Parcelize
    data class LeftToSpendNegativeSummary(
        val leftToSpend: Amount,
        val month: YearMonth
    ) : InsightData()

    @Parcelize
    data class LeftToSpendPositiveBeginningMonth(
        val amountDifference: Amount,
        val leftToSpendStatistics: LeftToSpendStatisticsMid,
        val month: YearMonth,
        val totalExpense: Amount
    ) : InsightData()

    @Parcelize
    data class LeftToSpendNegativeBeginningMonth(
        val amountDifference: Amount,
        val leftToSpendStatistics: LeftToSpendStatisticsMid,
        val month: YearMonth,
        val totalExpense: Amount
    ) : InsightData()

    @Parcelize
    data class LeftToSpendPositiveSummarySavingsAccount(
        val leftAmount: Amount,
        val month: YearMonth
    ) : InsightData()

    @Parcelize
    data class LeftToSpendPositiveFinalWeek(
        val amountDifference: Amount,
        val leftToSpendPerDay: Amount,
        val leftToSpendStatistics: LeftToSpendStatisticsMid,
        val month: YearMonth
    ) : InsightData()
}
