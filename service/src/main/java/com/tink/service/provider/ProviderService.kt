package com.tink.service.provider

import com.tink.model.provider.Provider

interface ProviderService {

    suspend fun listSuggestions(): List<Provider>

    suspend fun listProviders(filter: ProviderFilter?): List<Provider>
}

data class ProviderFilter(
    val includeDemoProviders: Boolean? = null,
    val includeNonDemoProviders: Boolean? = null,
    val requireCapability: Provider.Capability? = null
)
