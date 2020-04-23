package com.tink.service.budget

import com.tink.model.budget.BudgetCreateOrUpdateDescriptor
import com.tink.model.budget.BudgetPeriod
import com.tink.model.budget.BudgetSpecification
import com.tink.model.budget.BudgetSummary
import com.tink.model.budget.BudgetTransaction
import com.tink.service.handler.ResultHandler
import org.threeten.bp.Instant
import javax.inject.Inject

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

class BudgetServiceImpl @Inject constructor() : BudgetService {
    override fun createBudget(
        descriptor: BudgetCreateOrUpdateDescriptor,
        mutationHandler: ResultHandler<BudgetSpecification>
    ) {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun updateBudget(
        descriptor: BudgetCreateOrUpdateDescriptor,
        mutationHandler: ResultHandler<BudgetSpecification>
    ) {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteBudget(id: String, mutationHandler: ResultHandler<Unit>) {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun listBudgets(mutationHandler: ResultHandler<List<BudgetSummary>>) {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun listTransactionsForBudget(
        budgetId: String,
        start: Instant,
        end: Instant,
        mutationHandler: ResultHandler<List<BudgetTransaction>>
    ) {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun budgetPeriodDetails(
        budgetId: String,
        start: Instant,
        end: Instant,
        mutationHandler: ResultHandler<Pair<BudgetSpecification, List<BudgetPeriod>>>
    ) {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }
}