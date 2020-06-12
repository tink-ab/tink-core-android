package com.tink.service.provider

import com.tink.model.provider.Provider

interface ProviderService {

    suspend fun listSuggestions(): List<Provider>

    suspend fun listProviders(filter: ProviderFilter?): List<Provider>

    suspend fun getProvider(providerName: String): Provider?
}

/**
 * A filter to describe which providers should be included in the response
 *
 * @property includeDemoProviders If set to true, providers of type [TEST][Provider.Type.TEST] will
 * be added in the response list. Default: false
 * @property includeNonDemoProviders If set to false, providers with a type different than
 * [TEST][Provider.Type.TEST] will be removed from the response list. Default: true
 * @property requireCapability Use the capability to only list providers with a specific capability.
 * By default the list will not be filtered by capability
 */
data class ProviderFilter(
    val includeDemoProviders: Boolean = false,
    val includeNonDemoProviders: Boolean = true,
    val requireCapability: Provider.Capability? = null
)
