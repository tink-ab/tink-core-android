package com.tink.service.credentials

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CredentialsRestConvertersTest {

    private val supplementalInformationString =
        "[{\"defaultValue\":null,\"description\":\"Security Code\",\"exposed\":true,\"children\":null,\"helpText\":\"Login using your Card Reader. Enter the security code and press Ok. Provide the given return code in the input field to continue \n\",\"hint\":null,\"immutable\":true,\"masked\":false,\"maxLength\":null,\"minLength\":null,\"name\":\"loginDescriptionField\",\"numeric\":false,\"optional\":false,\"options\":null,\"pattern\":null,\"patternError\":null,\"type\":null,\"value\":\"8249\",\"sensitive\":false,\"checkbox\":false,\"additionalInfo\":null},{\"defaultValue\":null,\"description\":\"Input Code\",\"exposed\":true,\"children\":null,\"helpText\":null,\"hint\":null,\"immutable\":false,\"masked\":false,\"maxLength\":null,\"minLength\":null,\"name\":\"loginInputField\",\"numeric\":false,\"optional\":false,\"options\":null,\"pattern\":null,\"patternError\":null,\"type\":null,\"value\":null,\"sensitive\":false,\"checkbox\":false,\"additionalInfo\":null}]"

    @Test
    fun `convert string to supplemental information`() {

        val resultMap = mapSupplementalInformation(supplementalInformationString)
            .associateBy { it.name }

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
}
