package com.tink.service.budget

import com.tink.model.budget.BudgetCreateOrUpdateDescriptor
import com.tink.model.budget.BudgetPeriod
import com.tink.model.budget.BudgetSpecification
import com.tink.model.budget.BudgetSummary
import com.tink.model.budget.BudgetTransaction
import com.tink.model.budget.OneOffPeriodicity
import com.tink.model.budget.RecurringPeriodicity
import com.tink.rest.apis.BudgetApi
import com.tink.rest.models.CreateOneOffBudgetRequest
import com.tink.rest.models.CreateRecurringBudgetRequest
import com.tink.rest.models.UpdateBudgetRequest
import com.tink.service.misc.toDto
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

class BudgetServiceImpl @Inject constructor(
    val api: BudgetApi
) : BudgetService {
    override suspend fun createBudget(descriptor: BudgetCreateOrUpdateDescriptor): BudgetSpecification {
        return with(descriptor) {
            when (val periodicity = this.periodicity) {
                is OneOffPeriodicity -> api.createOneOff(
                    CreateOneOffBudgetRequest(
                        name = name,
                        amount = targetAmount.toDto(),
                        filter = filter.toDto(),
                        oneOffPeriodicity = periodicity.toDto()
                    )
                )
                is RecurringPeriodicity -> api.createRecurring(
                    CreateRecurringBudgetRequest(
                        name = name,
                        amount = targetAmount.toDto(),
                        filter = filter.toDto(),
                        recurringPeriodicity = periodicity.toDto()
                    )
                )
            }.budgetSpecification!!.toCoreModel()
        }
    }

    override suspend fun updateBudget(descriptor: BudgetCreateOrUpdateDescriptor): BudgetSpecification {
        requireNotNull(descriptor.id) { throw IllegalArgumentException("Budget id cannot be null for request to update budget") }
        return with(descriptor) {
            when (val periodicity = this.periodicity) {
                is OneOffPeriodicity -> api.update(
                    id = id!!,
                    body = UpdateBudgetRequest(
                        name = name,
                        amount = targetAmount.toDto(),
                        filter = filter.toDto(),
                        oneOffPeriodicity = periodicity.toDto()
                    )
                )
                is RecurringPeriodicity -> api.update(
                    id = id!!,
                    body = UpdateBudgetRequest(
                        name = name,
                        amount = targetAmount.toDto(),
                        filter = filter.toDto(),
                        recurringPeriodicity = periodicity.toDto()
                    )
                )
            }.budgetSpecification!!.toCoreModel()
        }
    }

    override suspend fun deleteBudget(id: String) {
        api.delete(id)
    }

    override suspend fun listBudgets(): List<BudgetSummary> =
        api.listSummaries(includeArchived = false)
            .budgetSummaries
            ?.map { it.toCoreModel() }
            ?: emptyList()

    override suspend fun listTransactionsForBudget(
        budgetId: String,
        start: Instant,
        end: Instant
    ): List<BudgetTransaction> =
        api.getTransactions(
            id = budgetId,
            start = start.toEpochMilli(),
            end = end.toEpochMilli()
        ).transactions?.map { it.toCoreModel() } ?: emptyList()

    override suspend fun budgetPeriodDetails(
        budgetId: String,
        start: Instant,
        end: Instant
    ): Pair<BudgetSpecification, List<BudgetPeriod>> {
        return api.getDetails(
            id = budgetId,
            start = start.toEpochMilli(),
            end = end.toEpochMilli()
        ).let { response ->
            val budgetSpecification = response.budgetSpecification!!.toCoreModel()
            val budgetPeriods =
                response.budgetPeriods
                    ?.map { it.toCoreModel(budgetSpecification.amount) }
                    ?: emptyList()
            budgetSpecification to budgetPeriods
        }
    }
}