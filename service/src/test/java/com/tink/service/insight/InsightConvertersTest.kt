package com.tink.service.insight

import com.tink.model.insights.InsightState
import com.tink.model.insights.InsightType
import com.tink.rest.models.ActionableInsight
import com.tink.rest.tools.GeneratedCodeConverters
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class InsightConvertersTest {

    private val insightJson = "{\n" +
            "    \"createdTime\": 1549976786000,\n" +
            "    \"data\": {\n" +
            "      \"type\": \"ACCOUNT_BALANCE_LOW\"\n" +
            "    },\n" +
            "    \"description\": \"The balance on your bank account x is low. \\nDo you want to transfer money to this account?\",\n" +
            "    \"id\": \"e2b746ed27c542ce846a8d693474df21\",\n" +
            "    \"insightActions\": [\n" +
            "      {\n" +
            "        \"data\": {\n" +
            "          \"type\": \"CREATE_TRANSFER\"\n" +
            "        },\n" +
            "        \"label\": \"Make transfer\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"title\": \"Your balance on bank account x is low\",\n" +
            "    \"type\": \"ACCOUNT_BALANCE_LOW\",\n" +
            "    \"userId\": \"d9f134ee2eb44846a4e02990ecc8d32e\"\n" +
            "  }"

    @Test
    fun `simple insight conversion`() {

        val insightDto = GeneratedCodeConverters.moshi
            .adapter(ActionableInsight::class.java)
            .fromJson(insightJson)

        val insight = insightDto!!.toCoreModel(InsightState.Active)

        assertThat(insight.title).isEqualTo("Your balance on bank account x is low")
        assertThat(insight.description)
            .isEqualTo("The balance on your bank account x is low. \nDo you want to transfer money to this account?")
        assertThat(insight.id).isEqualTo("e2b746ed27c542ce846a8d693474df21")
        assertThat(insight.type).isEqualTo(InsightType.ACCOUNT_BALANCE_LOW)
        assertThat(insight.created.toEpochMilli()).isEqualTo(1549976786000)
    }
}
