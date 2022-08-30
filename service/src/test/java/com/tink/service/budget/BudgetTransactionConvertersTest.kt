package com.tink.service.budget

import com.tink.rest.tools.GeneratedCodeConverters
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import com.tink.rest.models.BudgetTransaction as BudgetTransactionDto

internal class BudgetTransactionConvertersTest {

    private val budgetTransactionJson =
        """
        {
            "accountId": "325ee4ccf579450ca59d89ee54fa7e40",
            "amount": {
                "currencyCode": "EUR",
                "scale": 2,
                "unscaledValue": 1250
            },
            "categoryCode": "expenses:food.coffee",
            "date": 1549976786000,
            "description": "Monmouth Coffee Company",
            "dispensableAmount": {
                "currencyCode": "EUR",
                "scale": 2,
                "unscaledValue": 1050
            },
            "id": "e2b746ed27c542ce846a8d693474df21",
            "pending":true
        }
        """

    private val budgetTransactionJsonAdapter =
        GeneratedCodeConverters.moshi.adapter(BudgetTransactionDto::class.java)

    @Test
    fun `convert string to budget transaction`() {

        val budgetTransactionDto = budgetTransactionJsonAdapter.fromJson(budgetTransactionJson)

        val budgetTransaction = budgetTransactionDto!!.toCoreModel()

        assertThat(budgetTransaction.id).isEqualTo("e2b746ed27c542ce846a8d693474df21")
        assertThat(budgetTransaction.accountId).isEqualTo("325ee4ccf579450ca59d89ee54fa7e40")
        assertThat(budgetTransaction.amount.currencyCode).isEqualTo("EUR")
        assertThat(budgetTransaction.amount.value.scale).isEqualTo(2)
        assertThat(budgetTransaction.amount.value.unscaledValue).isEqualTo(1250)
        assertThat(budgetTransaction.dispensableAmount.currencyCode).isEqualTo("EUR")
        assertThat(budgetTransaction.dispensableAmount.value.scale).isEqualTo(2)
        assertThat(budgetTransaction.dispensableAmount.value.unscaledValue).isEqualTo(1050)
        assertThat(budgetTransaction.categoryCode).isEqualTo("expenses:food.coffee")
        assertThat(budgetTransaction.description).isEqualTo("Monmouth Coffee Company")
        assertThat(budgetTransaction.date.toEpochMilli()).isEqualTo(1549976786000)
    }
}
