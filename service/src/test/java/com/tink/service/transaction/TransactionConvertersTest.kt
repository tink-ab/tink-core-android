package com.tink.service.transaction

import com.tink.model.misc.Amount
import com.tink.model.misc.ExactNumber
import com.tink.rest.models.SearchQuery
import com.tink.rest.tools.GeneratedCodeConverters
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.threeten.bp.Instant
import com.tink.rest.models.TransactionResponse as TransactionDto

internal class TransactionConvertersTest {

    private val transactionJson = "{\n" +
            "        \"accountId\": \"3fe2d96efacd4dc5994404a950f238a9\",\n" +
            "        \"amount\": 34.5,\n" +
            "        \"categoryId\": \"0e1bade6a7e3459eb794f27b7ba4cea0\",\n" +
            "        \"categoryType\": \"EXPENSES\",\n" +
            "        \"credentialsId\": \"65bc7a41a66e4ad1aad199bbfb3c5098\",\n" +
            "        \"currencyDenominatedAmount\": {\n" +
            "          \"currencyCode\": \"EUR\",\n" +
            "          \"scale\": 2,\n" +
            "          \"unscaledValue\": 1050\n" +
            "        },\n" +
            "        \"currencyDenominatedOriginalAmount\": {\n" +
            "          \"currencyCode\": \"EUR\",\n" +
            "          \"scale\": 2,\n" +
            "          \"unscaledValue\": 1150\n" +
            "        },\n" +
            "        \"date\": 1455740874875,\n" +
            "        \"description\": \"Stadium\",\n" +
            "        \"dispensableAmount\": 0,\n" +
            "        \"id\": \"79c6c9c27d6e42489e888e08d27205a1\",\n" +
            "        \"lastModified\": 1455740874875,\n" +
            "        \"notes\": \"Delicious #cake #wedding\",\n" +
            "        \"originalAmount\": 34.5,\n" +
            "        \"originalDate\": 1455741874975,\n" +
            "        \"originalDescription\": \"Stadium Sergelg Stockholm\",\n" +
            "        \"partnerPayload\": {},\n" +
            "        \"parts\": [\n" +
            "          {\n" +
            "            \"amount\": 34.5,\n" +
            "            \"categoryId\": \"0e1bade6a7e3459eb794f27b7ba4cea0\",\n" +
            "            \"counterpartDescription\": \"Stadium Sergelg Stockholm\",\n" +
            "            \"counterpartId\": \"79c6c9c27d6e42489e888e08d27205a1\",\n" +
            "            \"counterpartTransactionAmount\": 10.0,\n" +
            "            \"counterpartTransactionId\": \"d030a7b0840547428aa2fd07026e9a77\",\n" +
            "            \"date\": 1455740874875,\n" +
            "            \"id\": \"7303ff128531463bbed358bbf9e23f31\",\n" +
            "            \"lastModified\": 1455740874875\n" +
            "          }\n" +
            "        ],\n" +
            "        \"payload\": {},\n" +
            "        \"pending\": false,\n" +
            "        \"timestamp\": 1464543093494,\n" +
            "        \"type\": \"CREDIT_CARD\",\n" +
            "        \"upcoming\": false,\n" +
            "        \"userId\": \"d9f134ee2eb44846a4e02990ecc8d32e\",\n" +
            "        \"userModified\": false\n" +
            "      }"

    @Test
    fun `test simple transaction mapping`() {

        val transactionDto = GeneratedCodeConverters.moshi
            .adapter(TransactionDto::class.java)
            .fromJson(transactionJson)

        with(transactionDto!!.toCoreModel()) {
            assertThat(accountId).isEqualTo("3fe2d96efacd4dc5994404a950f238a9")
            assertThat(amount).isEqualTo(Amount(ExactNumber(1050, 2), "EUR"))
            assertThat(date).isEqualTo(Instant.ofEpochMilli(1455740874875))
            assertThat(id).isEqualTo("79c6c9c27d6e42489e888e08d27205a1")
            assertThat(description).isEqualTo("Stadium")
            assertThat(notes).isEqualTo("Delicious #cake #wedding")
            assertThat(upcoming).isFalse()
            assertThat(pending).isFalse()
            assertThat(originalDescription).isEqualTo("Stadium Sergelg Stockholm")
            assertThat(originalDate).isEqualTo(Instant.ofEpochMilli(1455741874975))
            assertThat(originalAmount).isEqualTo(Amount(ExactNumber(1150, 2), "EUR"))
            // TODO categoryCode, tags, dispensable amount, inserted, details
        }
    }

    @Test
    fun `search query conversion`() {
        GeneratedCodeConverters.moshi.adapter(SearchQuery::class.java).toJson(SearchQuery())
    }
}