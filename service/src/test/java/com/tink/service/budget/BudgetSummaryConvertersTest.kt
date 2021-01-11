package com.tink.service.budget

import com.tink.model.budget.OneOffPeriodicity
import com.tink.rest.models.BudgetSummary as BudgetSummaryDto
import com.tink.rest.tools.GeneratedCodeConverters
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BudgetSummaryConvertersTest {

    private val budgetSummaryJson =
        """
        {
            "budgetPeriods": {
                "end": 1552395986000,
                "spentAmount": {
                    "currencyCode": "EUR",
                    "scale": 2,
                    "unscaledValue": 1050
                },
                "start": 1549976786000
            },
            "budgetSpecification": {
                "amount": {
                    "currencyCode": "EUR",
                        "scale": 2,
                        "unscaledValue": 1250
                },
                "archived": false,
                "filter": {
                    "accounts": [
                        {
                            "id": "325ee4ccf579450ca59d89ee54fa7e40"
                        }
                    ],
                    "categories": [
                        {
                            "code": "expenses:food.coffee"
                        }
                    ],
                    "freeTextQuery": "Monmouth Coffee",
                    "tags": [
                        {
                            "key": "coffee"
                        }
                    ]
                },
                "id": "e2b746ed27c542ce846a8d693474df21",
                "name": "Coffee budget",
                "oneOffPeriodicity": {
                    "end": 1552395986000,
                    "start": 1549976786000
                },
                "periodicityType": "ONE_OFF",
                "recurringPeriodicity": {
                    "periodUnit": "WEEK"
                }
            }
        }
        """

    private val budgetSummaryJsonAdapter =
        GeneratedCodeConverters.moshi.adapter(BudgetSummaryDto::class.java)

    @Test
    fun `convert string to budget summary`() {

        val budgetSummaryDto = budgetSummaryJsonAdapter.fromJson(budgetSummaryJson)

        val budgetSummary = budgetSummaryDto!!.toCoreModel()

        val budgetSpecification = budgetSummary.budgetSpecification
        val budgetPeriod = budgetSummary.budgetPeriod

        assertThat(budgetSpecification.id).isEqualTo("e2b746ed27c542ce846a8d693474df21")
        assertThat(budgetSpecification.name).isEqualTo("Coffee budget")
        assertThat(budgetSpecification.amount.currencyCode).isEqualTo("EUR")
        assertThat(budgetSpecification.amount.value.scale).isEqualTo(2)
        assertThat(budgetSpecification.amount.value.unscaledValue).isEqualTo(1250)
        assertThat(budgetSpecification.archived).isEqualTo(false)

        val periodicity = budgetSpecification.periodicity as OneOffPeriodicity
        assertThat(periodicity.start.toEpochMilli()).isEqualTo(1549976786000)
        assertThat(periodicity.end.toEpochMilli()).isEqualTo(1552395986000)

        val filter = budgetSpecification.filter
        assertThat(filter.accounts).isNotEmpty
        assertThat(filter.accounts.size).isEqualTo(1)
        assertThat(filter.accounts[0].id).isEqualTo("325ee4ccf579450ca59d89ee54fa7e40")
        assertThat(filter.categories).isNotEmpty
        assertThat(filter.categories.size).isEqualTo(1)
        assertThat(filter.categories[0].code).isEqualTo("expenses:food.coffee")
        assertThat(filter.tags).isNotEmpty
        assertThat(filter.tags.size).isEqualTo(1)
        assertThat(filter.tags[0].key).isEqualTo("coffee")
        assertThat(filter.freeTextQuery).isEqualTo("Monmouth Coffee")

        assertThat(budgetPeriod.start.toEpochMilli()).isEqualTo(1549976786000)
        assertThat(budgetPeriod.end.toEpochMilli()).isEqualTo(1552395986000)
        assertThat(budgetPeriod.budgetAmount.currencyCode).isEqualTo(budgetSpecification.amount.currencyCode)
        assertThat(budgetPeriod.budgetAmount.value.scale).isEqualTo(budgetSpecification.amount.value.scale)
        assertThat(budgetPeriod.budgetAmount.value.unscaledValue).isEqualTo(budgetSpecification.amount.value.unscaledValue)
        assertThat(budgetPeriod.spentAmount.currencyCode).isEqualTo("EUR")
        assertThat(budgetPeriod.spentAmount.value.scale).isEqualTo(2)
        assertThat(budgetPeriod.spentAmount.value.unscaledValue).isEqualTo(1050)
    }
}
