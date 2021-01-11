package com.tink.model.budget

import android.os.Parcelable
import com.tink.model.misc.Amount
import kotlinx.android.parcel.Parcelize
import org.threeten.bp.Instant

typealias BudgetSummary = Budget.Summary
typealias BudgetPeriod = Budget.Period
typealias BudgetTransaction = Budget.Transaction
typealias BudgetSpecification = Budget.Specification
typealias RecurringPeriodicity = Budget.Periodicity.Recurring
typealias OneOffPeriodicity = Budget.Periodicity.OneOff

class Budget {

    @Parcelize
    data class Summary(val budgetSpecification: Specification, val budgetPeriod: Period) :
        Parcelable

    @Parcelize
    data class Period(
        val start: Instant,
        val end: Instant,
        val spentAmount: Amount,
        val budgetAmount: Amount
    ) : Parcelable

    @Parcelize
    data class Transaction(
        val id: String,
        val accountId: String,
        val amount: Amount,
        val dispensableAmount: Amount,
        val categoryCode: String,
        val description: String,
        val date: Instant
    ) : Parcelable

    @Parcelize
    data class Specification(
        val id: String,
        val name: String,
        val amount: Amount,
        val periodicity: Periodicity,
        val archived: Boolean,
        val filter: Filter
    ) : Parcelable {

        @Parcelize
        data class Filter(
            val accounts: List<Account>,
            val categories: List<Category>,
            val tags: List<Tag>,
            val freeTextQuery: String
        ) : Parcelable {
            @Parcelize
            data class Account(val id: String) : Parcelable

            @Parcelize
            data class Category(val code: String) : Parcelable

            @Parcelize
            data class Tag(val key: String) : Parcelable
        }
    }

    sealed class Periodicity : Parcelable {

        @Parcelize
        data class OneOff(
            val start: Instant,
            val end: Instant
        ) : Periodicity()

        @Parcelize
        data class Recurring(
            val unit: PeriodUnit
        ) : Periodicity() {
            enum class PeriodUnit { UNKNOWN, WEEK, MONTH, YEAR }
        }
    }
}

/**
 * Descriptor for creating or updating a budget.
 * If we have an id, it means we're updating an already existing budget.
 */
@Parcelize
data class BudgetCreateOrUpdateDescriptor(
    val id: String? = null,
    val name: String,
    val targetAmount: Amount,
    val filter: Budget.Specification.Filter,
    val description: String? = null,
    val periodicity: Budget.Periodicity
) : Parcelable
