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

    private val transactionJson = """
        {
            "accountId": "3fe2d96efacd4dc5994404a950f238a9",
            "amount": 34.5,
            "categoryId": "0e1bade6a7e3459eb794f27b7ba4cea0",
            "categoryType": "EXPENSES",
            "credentialsId": "65bc7a41a66e4ad1aad199bbfb3c5098",
            "currencyDenominatedAmount": {
              "currencyCode": "EUR",
              "scale": 2,
              "unscaledValue": 1050
            },
            "currencyDenominatedOriginalAmount": {
              "currencyCode": "EUR",
              "scale": 2,
              "unscaledValue": 1150
            },
            "date": 1455740874875,
            "description": "Stadium",
            "dispensableAmount": 0,
            "id": "79c6c9c27d6e42489e888e08d27205a1",
            "lastModified": 1455740874875,
            "notes": "Delicious #cake #wedding",
            "originalAmount": 34.5,
            "originalDate": 1455740874876,
            "originalDescription": "Stadium Sergelg Stockholm",
            "partnerPayload": {},
            "parts": [
              {
                "amount": 34.5,
                "categoryId": "0e1bade6a7e3459eb794f27b7ba4cea0",
                "counterpartDescription": "Stadium Sergelg Stockholm",
                "counterpartId": "79c6c9c27d6e42489e888e08d27205a1",
                "counterpartTransactionAmount": 10.0,
                "counterpartTransactionId": "d030a7b0840547428aa2fd07026e9a77",
                "date": 1455740874875,
                "id": "7303ff128531463bbed358bbf9e23f31",
                "lastModified": 1455740874875
              }
            ],
            "payload": {},
            "pending": false,
            "timestamp": 1464543093494,
            "type": "CREDIT_CARD",
            "upcoming": false,
            "userId": "d9f134ee2eb44846a4e02990ecc8d32e",
            "userModified": false
        }
    """.trimIndent()

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
            assertThat(categoryId).isEqualTo("0e1bade6a7e3459eb794f27b7ba4cea0")
            assertThat(notes).isEqualTo("Delicious #cake #wedding")
            assertThat(upcoming).isFalse()
            assertThat(pending).isFalse()
            assertThat(originalDescription).isEqualTo("Stadium Sergelg Stockholm")
            assertThat(originalDate).isEqualTo(Instant.ofEpochMilli(1455740874876))
            assertThat(originalAmount).isEqualTo(Amount(ExactNumber(1150, 2), "EUR"))
            assertThat(inserted).isEqualTo(Instant.ofEpochMilli(1464543093494))
            assertThat(tags).isEmpty()
        }
    }

    @Test
    fun `search query conversion`() {
        GeneratedCodeConverters.moshi.adapter(SearchQuery::class.java).toJson(SearchQuery())
    }
}