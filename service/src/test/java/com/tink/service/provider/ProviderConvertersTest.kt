package com.tink.service.provider

import com.tink.model.provider.Provider
import com.tink.rest.tools.GeneratedCodeConverters
import org.assertj.core.api.Assertions.assertThat
import com.tink.rest.models.Provider.AuthenticationFlowEnum as AuthenticationFlowDto
import org.junit.jupiter.api.Test

internal class ProviderConvertersTest {

    @Test
    fun `unknown capabilities should yield UNKNOWN`() {
        val adapter = GeneratedCodeConverters.moshi.adapter(ProviderCapabilityDto::class.java)
        val capabilityDto = adapter.fromJson("\"NONSENSECAPABILITY\"")

        assertThat(capabilityDto).isEqualTo(ProviderCapabilityDto.UNKNOWN)

        val capability = capabilityDto!!.toCoreModel()

        assertThat(capability).isEqualTo(Provider.Capability.UNKNOWN)
    }

    @Test
    fun `unknown type should yield OTHER`() {
        val adapter = GeneratedCodeConverters.moshi.adapter(ProviderTypeDto::class.java)
        val typeDto = adapter.fromJson("\"NONSENSE_TYPE\"")

        assertThat(typeDto).isEqualTo(ProviderTypeDto.OTHER)

        val type = typeDto!!.toProviderType()

        assertThat(type).isEqualTo(Provider.Type.OTHER)
    }

    @Test
    fun `unknown access type should yield OTHER`() {
        val adapter = GeneratedCodeConverters.moshi.adapter(ProviderAccessTypeDto::class.java)
        val accessTypeDto = adapter.fromJson("\"NONSENSEACCESSTYPE\"")

        assertThat(accessTypeDto).isEqualTo(ProviderAccessTypeDto.OTHER)

        val accessType = accessTypeDto!!.toAccessType()

        assertThat(accessType).isEqualTo(Provider.AccessType.OTHER)
    }

    @Test
    fun `unknown status should yield UNKNOWN`() {
        val adapter = GeneratedCodeConverters.moshi.adapter(ProviderStatusDto::class.java)
        val statusDto = adapter.fromJson("\"NONSENSESTATUS_FOO\"")

        assertThat(statusDto).isEqualTo(ProviderStatusDto.UNKNOWN)

        val status = statusDto!!.toProviderStatus()

        assertThat(status).isEqualTo(Provider.Status.UNKNOWN)
    }

    @Test
    fun `unknown authentication flow should yield UNKNOWN`() {
        val adapter = GeneratedCodeConverters.moshi.adapter(AuthenticationFlowDto::class.java)
        val authenticationFlowDto = adapter.fromJson("\"NONSENSE_AUTHENTICATION_FLOW\"")

        assertThat(authenticationFlowDto).isEqualTo(AuthenticationFlowDto.UNKNOWN)

        // TODO: Add when converter is implemented
    }

    @Test
    fun `unknown authentication user type should yield UNKNOWN`() {
        val adapter = GeneratedCodeConverters.moshi.adapter(ProviderAuthenticationUserTypeDto::class.java)
        val authenticationUserTypeDto = adapter.fromJson("\"NONSENSE_AUTHENTICATION_USER_TYPE\"")

        assertThat(authenticationUserTypeDto).isEqualTo(ProviderAuthenticationUserTypeDto.UNKNOWN)

        val authenticationUserType = authenticationUserTypeDto!!.toCoreModel()

        assertThat(authenticationUserType).isEqualTo(Provider.AuthenticationUserType.UNKNOWN)
    }
}