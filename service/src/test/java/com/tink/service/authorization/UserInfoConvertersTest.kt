package com.tink.service.authorization

import com.tink.model.user.UserProfile
import com.tink.rest.tools.GeneratedCodeConverters
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.threeten.bp.Instant
import com.tink.rest.models.User as UserDto

internal class UserInfoConvertersTest {

    private val userInfoString =
        "{\n" +
            "  \"appId\": \"56a33be25eb9443fbb696f7c61eabd94\",\n" +
            "  \"created\": 1471349422000,\n" +
            "  \"externalUserId\": \"2d3bd65493b549e1927d97a2d0683ab8\",\n" +
            "  \"flags\": [\n" +
            "    \"TRANSFERS\",\n" +
            "    \"TEST_PINK_ONBOARDING\"\n" +
            "  ],\n" +
            "  \"id\": \"6e68cc6287704273984567b3300c5822\",\n" +
            "  \"nationalId\": \"198410045701\",\n" +
            "  \"profile\": {\n" +
            "    \"cashbackEnabled\": false,\n" +
            "    \"currency\": \"SEK\",\n" +
            "    \"locale\": \"sv_SE\",\n" +
            "    \"market\": \"SE\",\n" +
            "    \"notificationSettings\": {\n" +
            "      \"balance\": false,\n" +
            "      \"budget\": false,\n" +
            "      \"doubleCharge\": false,\n" +
            "      \"einvoices\": false,\n" +
            "      \"fraud\": false,\n" +
            "      \"income\": false,\n" +
            "      \"largeExpense\": false,\n" +
            "      \"leftToSpend\": false,\n" +
            "      \"loanUpdate\": false,\n" +
            "      \"summaryMonthly\": false,\n" +
            "      \"summaryWeekly\": false,\n" +
            "      \"transaction\": false,\n" +
            "      \"unusualAccount\": false,\n" +
            "      \"unusualCategory\": false\n" +
            "    },\n" +
            "    \"periodAdjustedDay\": 25,\n" +
            "    \"periodMode\": \"MONTHLY_ADJUSTED\",\n" +
            "    \"timeZone\": \"Europe/Stockholm\"\n" +
            "  },\n" +
            "  \"username\": \"tinker@example.com\"\n" +
            "}"

    private val userInfoJsonAdapter =
        GeneratedCodeConverters.moshi.adapter(UserDto::class.java)

    @Test
    fun `convert string to user info`() {

        println(userInfoString)

        val userInfoDto = userInfoJsonAdapter.fromJson(userInfoString)

        val userInfo = userInfoDto?.toCoreModel()

        requireNotNull(userInfo)

        with(userInfo) {
            Assertions.assertThat(appId).isEqualTo("56a33be25eb9443fbb696f7c61eabd94")
            Assertions.assertThat(created).isEqualTo(Instant.ofEpochMilli(1471349422000))
            Assertions.assertThat(id).isEqualTo("6e68cc6287704273984567b3300c5822")
            Assertions.assertThat(profile.locale).isEqualTo("sv_SE")
            Assertions.assertThat(profile.market).isEqualTo("SE")
            Assertions.assertThat(profile.currency).isEqualTo("SEK")
            Assertions.assertThat(profile.timeZone).isEqualTo("Europe/Stockholm")
            Assertions.assertThat(flags).isNotEmpty
            Assertions.assertThat(flags[0]).isEqualTo("TRANSFERS")
            Assertions.assertThat(flags[1]).isEqualTo("TEST_PINK_ONBOARDING")
            Assertions.assertThat(username).isEqualTo("tinker@example.com")
            Assertions.assertThat(nationalId).isEqualTo("198410045701")

            Assertions.assertThat(profile.periodMode).isInstanceOf(UserProfile.PeriodMode.MonthlyAdjusted::class.java)
            val periodAdjustedDay = (profile.periodMode as UserProfile.PeriodMode.MonthlyAdjusted).periodAdjustedDayOfMonth
            Assertions.assertThat(periodAdjustedDay).isEqualTo(25)
        }
    }
}
