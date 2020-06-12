package com.tink.service.provider

import com.tink.model.provider.Provider
import com.tink.rest.apis.ProviderApi
import com.tink.service.di.ServiceScope
import javax.inject.Inject

@ServiceScope
internal class ProviderServiceImpl @Inject constructor(
    private val api: ProviderApi
) : ProviderService {

    override suspend fun listSuggestions(): List<Provider> =
        api.suggest().toProviderList()

    override suspend fun listProviders(
        filter: ProviderFilter?
    ): List<Provider> =
        api
            .list(
                capability = filter?.requireCapability?.name,
                includeTestProviders = filter?.includeDemoProviders,
                excludeNonTestProviders = filter?.includeNonDemoProviders?.not(),
                name = null
            )
            .toProviderList()

    override suspend fun getProvider(providerName: String) =
        api.list(
            name = providerName,
            capability = null,
            includeTestProviders = null,
            excludeNonTestProviders = null
        ).providers?.firstOrNull()?.toCoreModel()
}