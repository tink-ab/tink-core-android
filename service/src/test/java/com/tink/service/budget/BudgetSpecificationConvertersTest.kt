package com.tink.service.budget

import com.tink.model.budget.OneOffPeriodicity
import com.tink.rest.models.Budget as BudgetDto
import com.tink.rest.tools.GeneratedCodeConverters
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BudgetSpecificationConvertersTest {

    private val budgetJson =
        "{\n" +
                "    \"amount\": {\n" +
                "      \"currencyCode\": \"EUR\",\n" +
                "      \"scale\": 2,\n" +
                "      \"unscaledValue\": 1050\n" +
                "    },\n" +
                "    \"archived\": false,\n" +
                "    \"filter\": {\n" +
                "      \"accounts\": [\n" +
                "        {\n" +
                "          \"id\": \"325ee4ccf579450ca59d89ee54fa7e40\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"categories\": [\n" +
                "        {\n" +
                "          \"code\": \"expenses:food.coffee\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"freeTextQuery\": \"Monmouth Coffee\",\n" +
                "      \"tags\": [\n" +
                "        {\n" +
                "          \"key\": \"coffee\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    \"id\": \"e2b746ed27c542ce846a8d693474df21\",\n" +
                "    \"name\": \"Coffee budget\",\n" +
                "    \"oneOffPeriodicity\": {\n" +
                "      \"end\": 1552395986000,\n" +
                "      \"start\": 1549976786000\n" +
                "    },\n" +
                "    \"periodicityType\": \"ONE_OFF\",\n" +
                "    \"recurringPeriodicity\": {\n" +
                "      \"periodUnit\": \"WEEK\"\n" +
                "    }\n" +
                "  }\n"

    private val budgetJsonAdapter = GeneratedCodeConverters.moshi.adapter(BudgetDto::class.java)

    @Test
    fun `convert string to budget specification`() {

        val budgetSpecificationDto = budgetJsonAdapter.fromJson(budgetJson)

        val budgetSpecification = budgetSpecificationDto!!.toCoreModel()

        assertThat(budgetSpecification.id).isEqualTo("e2b746ed27c542ce846a8d693474df21")
        assertThat(budgetSpecification.name).isEqualTo("Coffee budget")
        assertThat(budgetSpecification.amount.currencyCode).isEqualTo("EUR")
        assertThat(budgetSpecification.amount.value.scale).isEqualTo(2)
        assertThat(budgetSpecification.amount.value.unscaledValue).isEqualTo(1050)
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
    }
}
