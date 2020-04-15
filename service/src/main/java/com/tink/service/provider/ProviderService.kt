package com.tink.service.provider

import com.tink.model.provider.Provider

interface ProviderService {

    suspend fun listSuggestions(): List<Provider>

    suspend fun listProviders(includeDemoProviders: Boolean): List<Provider>
}
