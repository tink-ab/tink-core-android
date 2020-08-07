package com.tink.service.budget

import com.tink.model.budget.BudgetCreateOrUpdateDescriptor
import com.tink.model.budget.BudgetPeriod
import com.tink.model.budget.BudgetSpecification
import com.tink.model.budget.BudgetSummary
import com.tink.model.budget.BudgetTransaction
import org.threeten.bp.Instant
import javax.inject.Inject

interface BudgetService {
    suspend fun createBudget(descriptor: BudgetCreateOrUpdateDescriptor): BudgetSpecification
    suspend fun updateBudget(descriptor: BudgetCreateOrUpdateDescriptor): BudgetSpecification
    suspend fun deleteBudget(id: String)
    suspend fun listBudgets(): List<BudgetSummary>

    suspend fun listTransactionsForBudget(
        budgetId: String,
        start: Instant,
        end: Instant
    ): List<BudgetTransaction>

    suspend fun budgetPeriodDetails(
        budgetId: String,
        start: Instant,
        end: Instant
    ): Pair<BudgetSpecification, List<BudgetPeriod>>
}

class BudgetServiceImpl @Inject constructor() : BudgetService {
    override suspend fun createBudget(descriptor: BudgetCreateOrUpdateDescriptor): BudgetSpecification {
        TODO("Not yet implemented")
    }

    override suspend fun updateBudget(descriptor: BudgetCreateOrUpdateDescriptor): BudgetSpecification {
        TODO("Not yet implemented")
    }

    override suspend fun deleteBudget(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun listBudgets(): List<BudgetSummary> {
        TODO("Not yet implemented")
    }

    override suspend fun listTransactionsForBudget(
        budgetId: String,
        start: Instant,
        end: Instant
    ): List<BudgetTransaction> {
        TODO("Not yet implemented")
    }

    override suspend fun budgetPeriodDetails(
        budgetId: String,
        start: Instant,
        end: Instant
    ): Pair<BudgetSpecification, List<BudgetPeriod>> {
        TODO("Not yet implemented")
    }
}