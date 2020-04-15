package com.tink.service.provider

import com.tink.model.provider.Provider
import com.tink.service.di.ServiceScope
import com.tink.service.generated.apis.ProviderApi
import javax.inject.Inject

@ServiceScope
class ProviderServiceImpl @Inject constructor(
    private val api: ProviderApi
) : ProviderService {

    override suspend fun listSuggestions(): List<Provider> =
        api.suggest().toProviderList()

    override suspend fun listProviders(
        includeDemoProviders: Boolean
    ): List<Provider> =
        api
            .list(
                capability = null,
                includeTestProviders = includeDemoProviders,
                excludeNonTestProviders = false,
                name = null
            )
            .toProviderList()
}