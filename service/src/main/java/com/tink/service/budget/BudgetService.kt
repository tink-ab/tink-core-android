package com.tink.service.budget

import com.tink.model.budget.BudgetCreateOrUpdateDescriptor
import com.tink.model.budget.BudgetPeriod
import com.tink.model.budget.BudgetSpecification
import com.tink.model.budget.BudgetSummary
import com.tink.model.budget.BudgetTransaction
import com.tink.service.handler.ResultHandler
import org.threeten.bp.Instant

interface BudgetService {
    fun createBudget(
        descriptor: BudgetCreateOrUpdateDescriptor,
        mutationHandler: ResultHandler<BudgetSpecification>
    )

    fun updateBudget(
        descriptor: BudgetCreateOrUpdateDescriptor,
        mutationHandler: ResultHandler<BudgetSpecification>
    )

    fun deleteBudget(
        id: String,
        mutationHandler: ResultHandler<Unit>
    )

    fun listBudgets(mutationHandler: ResultHandler<List<BudgetSummary>>)

    fun listTransactionsForBudget(
        budgetId: String,
        start: Instant,
        end: Instant,
        mutationHandler: ResultHandler<List<BudgetTransaction>>
    )

    fun budgetPeriodDetails(
        budgetId: String,
        start: Instant,
        end: Instant,
        mutationHandler: ResultHandler<Pair<BudgetSpecification, List<BudgetPeriod>>>
    )
}