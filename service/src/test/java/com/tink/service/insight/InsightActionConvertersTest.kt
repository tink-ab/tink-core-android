package com.tink.service.insight

import com.tink.model.insights.InsightAction
import com.tink.model.misc.Amount
import com.tink.model.misc.ExactNumber
import com.tink.rest.models.ActionableInsight
import com.tink.rest.models.InsightActionData
import com.tink.rest.tools.GeneratedCodeConverters
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class InsightActionConvertersTest {

    private val viewTransactionsActionDataJson = "{\n" +
            "\"type\": \"VIEW_TRANSACTIONS_BY_CATEGORY\"," +
            "    \"transactionIdsByCategory\": {\n" +
            "      \"expenses:food.coffee\": {\n" +
            "        \"transactionIds\": [\n" +
            "          \"f5dd06dafc504c1fa152be62408bcdff\"\n" +
            "        ]\n" +
            "      },\n" +
            "      \"expenses:food.groceries\": {\n" +
            "        \"transactionIds\": [\n" +
            "          \"0b18859117d645feaffbe5af5896e52a\",\n" +
            "          \"7a3d7bc881b64994a80fb9c1cdd6ded3\",\n" +
            "          \"c4d0f21822e84b7db54d54f7f33f0b47\"\n" +
            "        ]\n" +
            "      }\n" +
            "    }\n" +
            "  }"

    @Test
    fun `convert ViewTransactionsByCategoryData`() {

        val data = GeneratedCodeConverters.moshi
            .adapter(InsightActionData::class.java)
            .fromJson(viewTransactionsActionDataJson)
                as InsightActionData.ViewTransactionsByCategoryActionData

        val coffeeTransactions = data.transactionIdsByCategory
            .getValue("expenses:food.coffee")
            .transactionIds

        val groceryTransactions = data.transactionIdsByCategory
            .getValue("expenses:food.groceries")
            .transactionIds

        assertThat(coffeeTransactions).asList().contains("f5dd06dafc504c1fa152be62408bcdff")
        assertThat(groceryTransactions).asList()
            .contains("0b18859117d645feaffbe5af5896e52a").asList()
            .contains("7a3d7bc881b64994a80fb9c1cdd6ded3").asList()
            .contains("c4d0f21822e84b7db54d54f7f33f0b47")
    }

    private val acknowledgeActionData = "{\n \"type\": \"ACKNOWLEDGE\"\n}"

    @Test
    fun `convert Acknowledge`() {
        val data = GeneratedCodeConverters.moshi
            .adapter(InsightActionData::class.java)
            .fromJson(acknowledgeActionData)

        assertThat(data is InsightActionData.Acknowledge)
    }

    private val insightJson = "{\n" +
            "    \"createdTime\": 1549976786000,\n" +
            "    \"data\": {\n" +
            "      \"type\": \"ACCOUNT_BALANCE_LOW\",\n" +
            "      \"accountId\": \"c6f26025fbb949a08348e2f73f0ae12c\",\n" +
            "      \"balance\": {\n" +
            "        \"currencyCode\": \"EUR\",\n" +
            "        \"amount\": 2.42\n" +
            "        }\n" +
            "    },\n" +
            "    \"description\": \"The balance on your bank account x is low. \\nDo you want to transfer money to this account?\",\n" +
            "    \"id\": \"e2b746ed27c542ce846a8d693474df21\",\n" +
            "    \"insightActions\": [\n" +
            "      {\n" +
            "        \"data\": {\n" +
            "          \"type\": \"CREATE_TRANSFER\",\n" +
            "          \"sourceAccount\": \"iban://SE9832691627751644451227\",\n" +
            "          \"destinationAccount\": \"iban://NL41INGB1822913977\",\n" +
            "          \"amount\": {\n" +
            "               \"currencyCode\": \"EUR\",\n" +
            "               \"amount\": 30.00\n" +
            "           },\n" +
            "           \"sourceAccountNumber\": \"1234567890\",\n" +
            "           \"destinationAccountNumber\": \"1234098765\"" +
            "           },\n" +
            "          \"label\": \"Make transfer\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"title\": \"Your balance on bank account x is low\",\n" +
            "    \"type\": \"ACCOUNT_BALANCE_LOW\",\n" +
            "    \"userId\": \"d9f134ee2eb44846a4e02990ecc8d32e\"\n" +
            "  }"

    @Test
    fun `convert basic insight`() {
        val insight = GeneratedCodeConverters.moshi
            .adapter(ActionableInsight::class.java)
            .fromJson(insightJson)!!
            .toCoreModel()

        val action = insight.actions.first()
        assertThat(action.actionType).isEqualTo(InsightAction.Type.CREATE_TRANSFER)

        val data = action.data as InsightAction.Data.CreateTransfer
        assertThat(data.amount).isEqualTo(Amount(ExactNumber(30.00), "EUR"))
        assertThat(data.sourceUri).isEqualTo("iban://SE9832691627751644451227")
        assertThat(data.destinationUri).isEqualTo("iban://NL41INGB1822913977")
    }

    private val viewBudgetActionDataJson =
        """
            {
                "type": "VIEW_BUDGET",
                "budgetId": "cbbac116e43c4b21b7013091ec03d590",
                "budgetPeriodStartTime": 1567296000000
            }
        """.trimIndent()

    @Test
    fun `convert ViewBudgetActionData`() {
        val data = GeneratedCodeConverters.moshi
            .adapter(InsightActionData::class.java)
            .fromJson(viewBudgetActionDataJson)
                as InsightActionData.ViewBudgetActionData
        val budgetId = data.budgetId
        val periodStartTime = data.budgetPeriodStartTime
        assertThat(budgetId).isEqualTo("cbbac116e43c4b21b7013091ec03d590")
        assertThat(periodStartTime).isEqualTo(1567296000000)
    }

    private val createBudgetActionDataJson =
        """
            {
                "type": "CREATE_BUDGET",
                "budgetSuggestion": {
                  "filter": {
                    "categories": ["expenses:food.bars"],
                    "accounts": ["d2b49640cbba4d8899a4886b6e8892f8"]
                  },
                  "amount": {
                    "currencyCode": "EUR",
                    "amount": 300.0
                  },
                  "periodicityType": "BUDGET_PERIODICITY_TYPE_RECURRING",
                  "recurringPeriodicityData": {
                    "periodUnit": "MONTH"
                  }
                }
            }
        """.trimIndent()

    @Test
    fun `convert CreateBudgetActionData`() {
        val data = GeneratedCodeConverters.moshi
            .adapter(InsightActionData::class.java)
            .fromJson(createBudgetActionDataJson)
                as InsightActionData.CreateBudgetActionData

        val suggestedAmount = data.budgetSuggestion.amount
        val suggestedFilter = data.budgetSuggestion.filter
        val periodicityType = data.budgetSuggestion.periodicityType
        val recurringPeriodicity = data.budgetSuggestion.recurringPeriodicityData
        val oneOffPeriodicity = data.budgetSuggestion.oneOffPeriodicityData

        assertThat(suggestedAmount!!.amount).isEqualTo(300.0)
        assertThat(suggestedAmount.currencyCode).isEqualTo("EUR")

        assertThat(suggestedFilter!!.categories).asList().contains("expenses:food.bars")
        assertThat(suggestedFilter.accounts).asList().contains("d2b49640cbba4d8899a4886b6e8892f8")

        assertThat(periodicityType!!.value).isEqualTo("BUDGET_PERIODICITY_TYPE_RECURRING")

        assertThat(oneOffPeriodicity).isNull()

        assertThat(recurringPeriodicity).isNotNull
        assertThat(recurringPeriodicity!!.periodUnit.value).isEqualTo("MONTH")
    }

    private val categorizeTransactionsActionDataJson = """
        {
            "type": "CATEGORIZE_TRANSACTIONS",
            "transactionIds": [
                "d2b49640cbba4d8899a4886b6e8892f8",
                "e8d668ddbe8d49ff81f40c8fb3b47c5d"
            ]
        }
    """.trimIndent()

    @Test
    fun `convert CategorizeTransactionsData`() {

        val data = GeneratedCodeConverters.moshi
            .adapter(InsightActionData::class.java)
            .fromJson(categorizeTransactionsActionDataJson)
                as InsightActionData.CategorizeTransactionsActionData

        val transactions = data.transactionIds

        assertThat(transactions).asList().contains("d2b49640cbba4d8899a4886b6e8892f8")
        assertThat(transactions).asList().contains("e8d668ddbe8d49ff81f40c8fb3b47c5d")
    }
}
