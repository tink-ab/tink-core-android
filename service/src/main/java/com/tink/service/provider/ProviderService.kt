package com.tink.service.provider

import com.tink.model.provider.Provider
import com.tink.service.handler.ResultHandler

interface ProviderService {

    fun listSuggestions(handler: ResultHandler<List<Provider>>)

    fun listProviders(handler: ResultHandler<List<Provider>>, includeDemoProviders: Boolean)
}
