package com.tink.service.account

import com.tink.model.account.Account
import com.tink.model.account.AccountDetails
import com.tink.model.misc.Amount
import com.tink.model.misc.ExactNumber
import com.tink.rest.models.AccountListResponse
import com.tink.rest.models.Account as AccountDto
import com.tink.rest.tools.GeneratedCodeConverters
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AccountConvertersTest {

    private val exampleAccount = """
        {
          "accounts": [
            {
              "accountExclusion": "AGGREGATION",
              "accountNumber": "1234-123456789",
              "balance": 34567.5,
              "closed": false,
              "credentialsId": "6e68cc6287704273984567b3300c5822",
              "currencyDenominatedBalance": {
                "currencyCode": "EUR",
                "scale": 2,
                "unscaledValue": 34567
              },
              "details": {
                "interest": 500,
                "nextDayOfTermsChange": 1552395986000,
                "numMonthsBound": 6,
                "type": "MORTGAGE"
              },
              "excluded": false,
              "favored": false,
              "financialInstitutionId": "6e68cc6287704273984567b3300c5822",
              "flags": "[\"MANDATE\"]",
              "holderName": "Thomas Alan Waits",
              "id": "a6bb87e57a8c4dd4874b241471a2b9e8",
              "identifiers": "[\"se://9999111111111111\"]",
              "name": "My account",
              "ownership": 0.5,
              "refreshed": 1455740874875,
              "transferDestinations": [
                {
                  "balance": 0,
                  "displayAccountNumber": "902090-0",
                  "displayBankName": null,
                  "matchesMultiple": false,
                  "name": "Barncancerfonden",
                  "type": "EXTERNAL",
                  "uri": "se-pg://9020900"
                }
              ],
              "type": "CHECKING"
            }
          ]
        }
    """.trimIndent()

    @Test
    fun `basic account conversion test`() {

        val accountListResponse = GeneratedCodeConverters.moshi
            .adapter(AccountListResponse::class.java).fromJson(exampleAccount)!!

        val account = accountListResponse.accounts!!.first().toCoreModel()

        assertThat(account.id).isEqualTo("a6bb87e57a8c4dd4874b241471a2b9e8")
        assertThat(account.credentialsId).isEqualTo("6e68cc6287704273984567b3300c5822")
        assertThat(account.accountNumber).isEqualTo("1234-123456789")
        assertThat(account.balance).isEqualTo(Amount(ExactNumber(34567, 2), "EUR"))
        assertThat(account.type).isEqualTo(Account.Type.CHECKING)
        assertThat(account.name).isEqualTo("My account")
        assertThat(account.ownership).isEqualTo(ExactNumber(5, 1))
        assertThat(account.excluded).isFalse()
        assertThat(account.favored).isFalse()
        assertThat(account.closed).isFalse()
        assertThat(account.holderName).isEqualTo("Thomas Alan Waits")
        assertThat(account.refreshed.toEpochMilli()).isEqualTo(1455740874875)
        assertThat(account.financialInstitutionID).isEqualTo("6e68cc6287704273984567b3300c5822")
        assertThat(account.accountExclusion).isEqualTo(Account.AccountExclusion.AGGREGATION)

        val accountDetails = account.accountDetails
        assertThat(accountDetails).isNotNull
        assertThat(accountDetails?.interest).isEqualTo(ExactNumber(500))
        assertThat(accountDetails?.numberOfMonthsBound).isEqualTo(6)
        assertThat(accountDetails?.type).isEqualTo(AccountDetails.Type.MORTGAGE)
        assertThat(accountDetails?.nextDayOfTermsChange?.toEpochMilli()).isEqualTo(1552395986000)

        val flags = account.flags
        assertThat(flags).isNotEmpty
        assertThat(flags).hasSize(1)
        assertThat(flags.first()).isEqualTo(Account.Flags.MANDATE)

        val identifiers = account.identifiers
        assertThat(identifiers).isNotEmpty
        assertThat(identifiers).hasSize(1)
        assertThat(identifiers.first()).isEqualTo("se://9999111111111111")

        val destinations = account.transferDestinations
        assertThat(destinations).isNotEmpty
        assertThat(destinations).hasSize(1)
        assertThat(destinations.first()).isEqualTo("se-pg://9020900")
    }

    @Test
    fun `strip one identifier`() {
        val identifierString = "[\"se://9999111111111111\"]"
        val identifiers = convertJsonStringArrayToList(identifierString)
        assertThat(identifiers).hasSize(1)
        assertThat(identifiers).contains("se://9999111111111111")
    }

    @Test
    fun `strip multiple identifiers`() {
        val identifierString = "[\"se://9999111111111111\",\"se://8888111111111111\"]"
        val identifiers = convertJsonStringArrayToList(identifierString)
        assertThat(identifiers).hasSize(2)
        assertThat(identifiers).contains("se://9999111111111111")
        assertThat(identifiers).contains("se://8888111111111111")
    }

    @Test
    fun `strip one flag`() {
        val flagString = "[\"MANDATE\"]"
        val flags = convertJsonStringArrayToList(flagString)
        assertThat(flags).hasSize(1)
        assertThat(flags).contains("MANDATE")
    }

    @Test
    fun `strip multiple flags`() {
        val flagString = "[\"MANDATE\",\"BUSINESS\"]"
        val flags = convertJsonStringArrayToList(flagString)
        assertThat(flags).hasSize(2)
        assertThat(flags).contains("MANDATE")
        assertThat(flags).contains("BUSINESS")
    }

    @Test
    fun `unknown type yields OTHER`() {
        val adapter = GeneratedCodeConverters.moshi.adapter(AccountDto.TypeEnum::class.java)
        val typeDto = adapter.fromJson("\"NONSENSETYPEJHJHJJGJH\"")

        assertThat(typeDto).isEqualTo(AccountDto.TypeEnum.UNKNOWN)

        val type = typeDto!!.toCoreModel()

        assertThat(type).isEqualTo(Account.Type.UNKNOWN)
    }

    @Test
    fun `unknown exclusion type yields UNKNOWN`() {

        val adapter = GeneratedCodeConverters.moshi.adapter(AccountDto.AccountExclusionEnum::class.java)
        val exclusionTypeDto = adapter.fromJson("\"NONSENSEEXCLUSIONTYPE\"")

        assertThat(exclusionTypeDto).isEqualTo(AccountDto.AccountExclusionEnum.UNKNOWN)
    }

    @Test
    fun `unknown flag yields UNKNOWN`() {
        val adapter = GeneratedCodeConverters.moshi.adapter(AccountDto.FlagsEnum::class.java)
        val flagDto = adapter.fromJson("\"NONSENSEFLAG999\"")

        assertThat(flagDto).isEqualTo(AccountDto.FlagsEnum.UNKNOWN)
    }
}
