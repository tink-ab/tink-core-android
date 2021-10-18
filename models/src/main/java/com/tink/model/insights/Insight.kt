package com.tink.model.insights

import android.os.Parcelable
import com.tink.model.budget.Budget
import com.tink.model.budget.BudgetFilter
import com.tink.model.budget.BudgetPeriodicity
import com.tink.model.misc.Amount
import com.tink.model.relations.AmountByCategory
import com.tink.model.relations.ExpensesByDay
import com.tink.model.time.YearMonth
import com.tink.model.time.YearWeek
import kotlinx.android.parcel.Parcelize
import org.threeten.bp.Instant

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
        data class ViewTransactions(val transactionIds: List<String>) : Data() {
            constructor(transactionId: String) : this(listOf(transactionId))
        }

        @Parcelize
        data class ViewTransactionsByCategory(
            val transactionsByCategory: Map<String, List<String>>
        ) : Data()
    }

    enum class Type(val value: String) {
        UNKNOWN("UNKNOWN"),
        ACKNOWLEDGE("ACKNOWLEDGE"),
        DISMISS("DISMISS"),
        VIEW_BUDGET("VIEW_BUDGET"),
        CREATE_BUDGET("CREATE_BUDGET"),
        CREATE_TRANSFER("CREATE_TRANSFER"),
        VIEW_TRANSACTION("VIEW_TRANSACTION"),
        CATEGORIZE_EXPENSE("CATEGORIZE_EXPENSE"),
        VIEW_TRANSACTIONS("VIEW_TRANSACTIONS"),
        CATEGORIZE_TRANSACTIONS("CATEGORIZE_TRANSACTIONS"),
        VIEW_TRANSACTIONS_BY_CATEGORY("VIEW_TRANSACTIONS_BY_CATEGORY")
    }
}

enum class InsightType {
    UNKNOWN,
    LEFT_TO_SPEND_HIGH,
    LEFT_TO_SPEND_LOW,
    ACCOUNT_BALANCE_LOW,
    INCREASE_CATEGORIZATION_LEVEL,
    ALL_BANKS_CONNECTED,
    EINVOICE,
    GENERIC_FRAUD,
    HIGHER_INCOME_THAN_CERTAIN_PERCENTILE,
    RATE_THIS_APP,
    RESIDENCE_DO_YOU_OWN_IT,
    MONTHLY_SUMMARY,
    WEEKLY_SUMMARY,
    EINVOICE_OVERDUE,
    BUDGET_OVERSPENT,
    BUDGET_CLOSE_NEGATIVE,
    BUDGET_CLOSE_POSITIVE,
    BUDGET_STREAK,
    BUDGET_SUCCESS,
    BUDGET_SUMMARY_OVERSPENT,
    BUDGET_SUMMARY_ACHIEVED,
    BUDGET_SUGGEST_CREATE_TOP_CATEGORY,
    BUDGET_SUGGEST_CREATE_TOP_PRIMARY_CATEGORY,
    BUDGET_SUGGEST_CREATE_FIRST,
    SINGLE_UNCATEGORIZED_TRANSACTION,
    LARGE_EXPENSE,
    DOUBLE_CHARGE,
    WEEKLY_SUMMARY_EXPENSES_BY_CATEGORY,
    WEEKLY_SUMMARY_EXPENSES_BY_DAY,
    WEEKLY_UNCATEGORIZED_TRANSACTIONS,
    MONTHLY_SUMMARY_EXPENSES_BY_CATEGORY
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

    // Simple data holders

    @Parcelize
    data class BudgetIdToPeriod(
        val budgetId: String,
        val budgetPeriod: Budget.Period
    ) : Parcelable
}
