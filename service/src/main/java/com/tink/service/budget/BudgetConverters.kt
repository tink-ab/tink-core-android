package com.tink.service.budget

import com.tink.model.budget.Budget
import com.tink.model.budget.Budget.Periodicity.Recurring.PeriodUnit
import com.tink.model.budget.Budget.Specification.Filter
import com.tink.model.budget.BudgetSpecification
import com.tink.model.budget.OneOffPeriodicity
import com.tink.model.budget.RecurringPeriodicity
import com.tink.rest.models.BudgetFilterAccount
import com.tink.rest.models.BudgetFilterCategory
import com.tink.rest.models.BudgetFilterTag
import com.tink.service.misc.toAmount
import com.tink.service.misc.toInstant
import com.tink.rest.models.Budget as BudgetDto
import com.tink.rest.models.Filter as BudgetFilterDto
import com.tink.rest.models.OneOffPeriodicity as OneOffPeriodicityDto
import com.tink.rest.models.RecurringPeriodicity as RecurringPeriodicityDto

fun Filter.toDto() =
    BudgetFilterDto(
        accounts = accounts.map { it.toDto() },
        categories = categories.map { it.toDto() },
        tags = tags.map { it.toDto() },
        freeTextQuery = freeTextQuery
    )

private fun Filter.Tag.toDto() = BudgetFilterTag(key)
private fun Filter.Category.toDto() = BudgetFilterCategory(code)
private fun Filter.Account.toDto() = BudgetFilterAccount(id)

fun OneOffPeriodicity.toDto() = OneOffPeriodicityDto(start.toEpochMilli(), end.toEpochMilli())
fun RecurringPeriodicity.toDto() = RecurringPeriodicityDto(
    periodUnit = when (unit) {
        PeriodUnit.WEEK -> RecurringPeriodicityDto.PeriodUnitEnum.WEEK
        PeriodUnit.MONTH -> RecurringPeriodicityDto.PeriodUnitEnum.MONTH
        PeriodUnit.YEAR -> RecurringPeriodicityDto.PeriodUnitEnum.YEAR
        PeriodUnit.UNKNOWN -> throw IllegalArgumentException("Cannot send UNKNOWN period unit")
    }
)

fun BudgetFilterDto.toCoreModel() =
    Filter(
        accounts = accounts?.map { it.toCoreModel() } ?: emptyList(),
        categories = categories?.map { it.toCoreModel() } ?: emptyList(),
        tags = tags?.map { it.toCoreModel() } ?: emptyList(),
        freeTextQuery = freeTextQuery ?: ""
    )

private fun BudgetFilterTag.toCoreModel() = Filter.Tag(key ?: "")
private fun BudgetFilterCategory.toCoreModel() = Filter.Category(code ?: "")
private fun BudgetFilterAccount.toCoreModel() = Filter.Account(id ?: "")

fun BudgetDto.toCoreModel(): BudgetSpecification {

    val periodicity = when (periodicityType!!) {
        BudgetDto.PeriodicityTypeEnum.ONE_OFF -> Budget.Periodicity.OneOff(
            oneOffPeriodicity!!.start.toInstant(),
            oneOffPeriodicity!!.end.toInstant()
        )
        BudgetDto.PeriodicityTypeEnum.RECURRING -> Budget.Periodicity.Recurring(
            unit = when (recurringPeriodicity?.periodUnit) {
                RecurringPeriodicityDto.PeriodUnitEnum.WEEK -> PeriodUnit.WEEK
                RecurringPeriodicityDto.PeriodUnitEnum.MONTH -> PeriodUnit.MONTH
                RecurringPeriodicityDto.PeriodUnitEnum.YEAR -> PeriodUnit.YEAR
                else -> PeriodUnit.UNKNOWN
            }
        )
    }

    return BudgetSpecification(
        id = id ?: "",
        name = name ?: "",
        amount = amount!!.toAmount(),
        archived = archived ?: false,
        periodicity = periodicity,
        filter = filter!!.toCoreModel()
    )
}
