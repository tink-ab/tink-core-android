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

    @Test
    fun `convert basic insight`() {
        val insight = GeneratedCodeConverters.moshi
            .adapter(ActionableInsight::class.java)
            .fromJson(insightJson)!!
            .toCoreModel()

        val data = insight.actions.first().data as InsightAction.Data.CreateTransfer

        assertThat(data.amount).isEqualTo(Amount(ExactNumber(30.00), "EUR"))
        assertThat(data.sourceUri).isEqualTo("iban://SE9832691627751644451227")
        assertThat(data.destinationUri).isEqualTo("iban://NL41INGB1822913977")
    }
}
