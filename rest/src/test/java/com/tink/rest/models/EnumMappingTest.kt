package com.tink.rest.models

import com.tink.rest.tools.GeneratedCodeConverters
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class EnumMappingTest {

    private val providerCapabilitiesAdapter =
        GeneratedCodeConverters.moshi.adapter(Provider.CapabilitiesEnum::class.java)

    @Test
    fun `known capability should yield the correct enum value`() {

        val known = providerCapabilitiesAdapter.fromJson("\"TRANSFERS\"")

        assertThat(known).isEqualTo(Provider.CapabilitiesEnum.TRANSFERS)
    }

    @Test
    fun `unknown capability should yield UNKNOWN`() {

        val unknown = providerCapabilitiesAdapter.fromJson("\"IHOHOVJKODBNVJEOBVJEB\"")

        assertThat(unknown).isEqualTo(Provider.CapabilitiesEnum.UNKNOWN)
    }

    private val providerAccessTypeAdapter =
        GeneratedCodeConverters.moshi.adapter(Provider.AccessTypeEnum::class.java)

    @Test
    fun `unknown provider access type should yield OTHER`() {

        val other = providerAccessTypeAdapter.fromJson("\"HNJEBNJBEJFEJBJB\"")

        assertThat(other).isEqualTo(Provider.AccessTypeEnum.OTHER)
    }
}
