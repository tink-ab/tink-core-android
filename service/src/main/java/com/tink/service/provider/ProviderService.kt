package com.tink.service.provider

import com.tink.model.provider.Provider
import com.tink.service.handler.ResultHandler

interface ProviderService {

    suspend fun listSuggestions(): List<Provider>

    suspend fun listProviders(includeDemoProviders: Boolean): List<Provider>
}
