package com.tink.service.account

import com.tink.model.account.Account
import com.tink.model.misc.Amount
import com.tink.model.misc.ExactNumber
import com.tink.rest.models.AccountListResponse
import com.tink.rest.tools.GeneratedCodeConverters
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AccountConvertersTest {

    private val exampleAccount = "{\n" +
            "  \"accounts\": [\n" +
            "    {\n" +
            "      \"accountExclusion\": \"NONE\",\n" +
            "      \"accountNumber\": \"1234-123456789\",\n" +
            "      \"balance\": 34567,\n" +
            "      \"closed\": false,\n" +
            "      \"credentialsId\": \"6e68cc6287704273984567b3300c5822\",\n" +
            "      \"currencyDenominatedBalance\": {\n" +
            "        \"currencyCode\": \"EUR\",\n" +
            "        \"scale\": 2,\n" +
            "        \"unscaledValue\": 1050\n" +
            "      },\n" +
            "      \"images\" : {\n" +
            "        \"icon\" : \"https://cdn.tink.se/provider-images/tink.png\",\n" +
            "        \"banner\" : null\n" +
            "      }," +
            "      \"details\": {\n" +
            "        \"interest\": 0,\n" +
            "        \"numMonthsBound\": 0,\n" +
            "        \"type\": \"CREDIT\"\n" +
            "      },\n" +
            "      \"excluded\": false,\n" +
            "      \"favored\": false,\n" +
            "      \"financialInstitutionId\": \"6e68cc6287704273984567b3300c5822\",\n" +
            "      \"holderName\": \"Thomas Alan Waits\",\n" +
            "      \"id\": \"a6bb87e57a8c4dd4874b241471a2b9e8\",\n" +
            "      \"identifiers\": \"[\\\"se://9999111111111111\\\"]\",\n" +
            "      \"name\": \"My account\",\n" +
            "      \"ownership\": 0,\n" +
            "      \"refreshed\": 1455740874875,\n" +
            "      \"transferDestinations\": [\n" +
            "        {\n" +
            "          \"balance\": 0,\n" +
            "          \"displayAccountNumber\": \"902090-0\",\n" +
            "          \"displayBankName\": null,\n" +
            "          \"matchesMultiple\": false,\n" +
            "          \"name\": \"Barncancerfonden\",\n" +
            "          \"type\": \"EXTERNAL\",\n" +
            "          \"uri\": \"se-pg://9020900\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"type\": \"CHECKING\"\n" +
            "    }\n" +
            "  ]\n" +
            "}"


    @Test
    fun `basic account conversion test`() {

        val accountListResponse = GeneratedCodeConverters.moshi
            .adapter(AccountListResponse::class.java).fromJson(exampleAccount)!!

        val account = accountListResponse.accounts!!.first().toCoreModel()

        assertThat(account.id).isEqualTo("a6bb87e57a8c4dd4874b241471a2b9e8")
        assertThat(account.credentialsId).isEqualTo("6e68cc6287704273984567b3300c5822")
        assertThat(account.accountNumber).isEqualTo("1234-123456789")
        assertThat(account.balance).isEqualTo(Amount(ExactNumber(1050, 2), "EUR"))
        assertThat(account.type).isEqualTo(Account.Type.TYPE_CHECKING)
        assertThat(account.name).isEqualTo("My account")
        assertThat(account.ownership).isEqualTo(ExactNumber(0,0))
        assertThat(account.images?.icon).isEqualTo("https://cdn.tink.se/provider-images/tink.png")
        assertThat(account.excluded).isFalse()
        assertThat(account.favored).isFalse()
        assertThat(account.closed).isFalse()
        //TODO: transactional
    }

}