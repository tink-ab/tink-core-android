package com.tink.service.credentials

import com.tink.service.generated.models.Credentials
import com.tink.service.generated.tools.GeneratedCodeConverters
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CredentialsRestConvertersTest {

    private val supplementalInformationString =
        "[{\\\"defaultValue\\\":null,\\\"description\\\":\\\"Security Code\\\",\\\"exposed\\\":true,\\\"children\\\":null,\\\"helpText\\\":\\\"Login using your Card Reader. Enter the security code and press Ok. Provide the given return code in the input field to continue \n\\\",\\\"hint\\\":null,\\\"immutable\\\":true,\\\"masked\\\":false,\\\"maxLength\\\":null,\\\"minLength\\\":null,\\\"name\\\":\\\"loginDescriptionField\\\",\\\"numeric\\\":false,\\\"optional\\\":false,\\\"options\\\":null,\\\"pattern\\\":null,\\\"patternError\\\":null,\\\"type\\\":null,\\\"value\\\":\\\"8249\\\",\\\"sensitive\\\":false,\\\"checkbox\\\":false,\\\"additionalInfo\\\":null},{\\\"defaultValue\\\":null,\\\"description\\\":\\\"Input Code\\\",\\\"exposed\\\":true,\\\"children\\\":null,\\\"helpText\\\":null,\\\"hint\\\":null,\\\"immutable\\\":false,\\\"masked\\\":false,\\\"maxLength\\\":null,\\\"minLength\\\":null,\\\"name\\\":\\\"loginInputField\\\",\\\"numeric\\\":false,\\\"optional\\\":false,\\\"options\\\":null,\\\"pattern\\\":null,\\\"patternError\\\":null,\\\"type\\\":null,\\\"value\\\":null,\\\"sensitive\\\":false,\\\"checkbox\\\":false,\\\"additionalInfo\\\":null}]"

    private val thirdPartyAuthenticationString =
        "{\\\"android\\\":{\\\"packageName\\\":null,\\\"requiredMinimumVersion\\\":0,\\\"intent\\\":\\\"https://cdn.tink.se/fake-bank/redirect-v2.html?code=1234&state=afa4efc3-cf6f-496f-8915-c24b433dfeed&preprod=false\\\"},\\\"ios\\\":{\\\"appStoreUrl\\\":null,\\\"scheme\\\":\\\"https\\\",\\\"deepLinkUrl\\\":\\\"https://cdn.tink.se/fake-bank/redirect-v2.html?code=1234&state=afa4efc3-cf6f-496f-8915-c24b433dfeed&preprod=false\\\"},\\\"desktop\\\":{\\\"url\\\":\\\"https://cdn.tink.se/fake-bank/redirect-v2.html?code=1234&state=afa4efc3-cf6f-496f-8915-c24b433dfeed&preprod=false\\\"},\\\"downloadTitle\\\":\\\"Download missing app\\\",\\\"downloadMessage\\\":null,\\\"upgradeTitle\\\":\\\"Update your app\\\",\\\"upgradeMessage\\\":null}"

    private val credentialsWithInfo =
        "{ \"fields\": { \"username\": \"198410045701\" }, \"id\": \"6e68cc6287704273984567b3300c5822\", \"providerName\": \"handelsbanken-bankid\", \"sessionExpiryDate\": 1493379467000, \"status\": \"UPDATED\", \"statusPayload\": \"Analyzed 1,200 out of 1,200 transactions.\", \"statusUpdated\": 1493379467000, \"supplementalInformation\": \"$supplementalInformationString\", \"type\": \"MOBILE_BANKID\", \"updated\": 1493379467000, \"userId\": \"c4ae034f96c740da91ae00022ddcac4d\" }"

    private val credentialsWithThirdParty =
        "{ \"fields\": { \"username\": \"198410045701\" }, \"id\": \"6e68cc6287704273984567b3300c5822\", \"providerName\": \"handelsbanken-bankid\", \"sessionExpiryDate\": 1493379467000, \"status\": \"UPDATED\", \"statusPayload\": \"Analyzed 1,200 out of 1,200 transactions.\", \"statusUpdated\": 1493379467000, \"supplementalInformation\": \"$thirdPartyAuthenticationString\", \"type\": \"MOBILE_BANKID\", \"updated\": 1493379467000, \"userId\": \"c4ae034f96c740da91ae00022ddcac4d\" }"

    private val credentialsWithAutostartToken =
        "{ \"fields\": { \"username\": \"198410045701\" }, \"id\": \"6e68cc6287704273984567b3300c5822\", \"providerName\": \"handelsbanken-bankid\", \"sessionExpiryDate\": 1493379467000, \"status\": \"UPDATED\", \"statusPayload\": \"Analyzed 1,200 out of 1,200 transactions.\", \"statusUpdated\": 1493379467000, \"supplementalInformation\": \"4d13a3a4-38e9-37d8-11cc-6e89982e4b70\", \"type\": \"MOBILE_BANKID\", \"updated\": 1493379467000, \"userId\": \"c4ae034f96c740da91ae00022ddcac4d\" }"

    @Test
    fun `convert string to supplemental information`() {

        println(credentialsWithInfo)

        val credentials = GeneratedCodeConverters.moshi
            .adapter(Credentials::class.java)
            .fromJson(credentialsWithInfo)

        val resultMap = credentials?.supplementalInformation?.fieldList
            ?.map { it.toCoreModel() }
            ?.associateBy { it.name }

        requireNotNull(resultMap)

        assertThat(resultMap.size).isEqualTo(2)

        with(resultMap["loginInputField"] ?: error("loginInputField not mapped")) {
            assertThat(value).isEmpty()
            assertThat(validationRules.isOptional).isEqualTo(false)
            assertThat(validationRules.pattern).isEmpty()
            assertThat(attributes.helpText).isEmpty()
            assertThat(attributes.inputType.isNumeric).isEqualTo(false)
            assertThat(attributes.inputType.isImmutable).isEqualTo(false)
            assertThat(attributes.description).isEqualTo("Input Code")
        }

        with(resultMap["loginDescriptionField"] ?: error("loginDescriptionField not mapped")) {
            assertThat(value).isEqualTo("8249")
            assertThat(validationRules.isOptional).isEqualTo(false)
            assertThat(validationRules.pattern).isEmpty()
            assertThat(attributes.helpText).isEqualTo("Login using your Card Reader. Enter the security code and press Ok. Provide the given return code in the input field to continue \n")
            assertThat(attributes.inputType.isNumeric).isEqualTo(false)
            assertThat(attributes.inputType.isImmutable).isEqualTo(true)
            assertThat(attributes.description).isEqualTo("Security Code")
        }
    }

    @Test
    fun `convert string to third party authentication`() {

        val credentials = GeneratedCodeConverters.moshi
            .adapter(Credentials::class.java)
            .fromJson(credentialsWithThirdParty)

        val result = credentials?.supplementalInformation?.thirdPartyAuthentication?.toCoreModel()

        requireNotNull(result)

        with(result) {
            assertThat(downloadTitle).isEqualTo("Download missing app")
            assertThat(downloadMessage).isEmpty()
            assertThat(upgradeTitle).isEqualTo("Update your app")
            assertThat(upgradeMessage).isEmpty()
        }
    }

    @Test
    fun `convert supplementalInfo to autostart token`() {

        val credentials = GeneratedCodeConverters.moshi
            .adapter(Credentials::class.java)
            .fromJson(credentialsWithAutostartToken)

        val token = credentials?.supplementalInformation?.rawStringInfo

        assertThat(token).isNotEmpty()

        val result = createThirdPartyAuthFromAutostartToken(token)

        requireNotNull(result)

        assertThat(result.android?.intent).isEqualTo("bankid:///?autostarttoken=4d13a3a4-38e9-37d8-11cc-6e89982e4b70")
        assertThat(result.android?.packageName).isEqualTo("com.bankid.bus")
        assertThat(result.downloadTitle).isEqualTo("Download Mobile BankID")
        assertThat(result.downloadMessage).isEqualTo("You need to install the Mobile BankID app to authenticate")
    }
}
