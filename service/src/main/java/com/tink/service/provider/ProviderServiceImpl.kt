package com.tink.service.provider

import com.tink.model.provider.Provider
import com.tink.service.di.ServiceScope
import com.tink.service.generated.apis.ProviderApi
import com.tink.service.generated.models.AuthenticatedUser
import javax.inject.Inject

@ServiceScope
class ProviderServiceImpl @Inject constructor(
    private val api: ProviderApi
) : ProviderService {

    override suspend fun listSuggestions(): List<Provider> =
        api.suggest(body = AuthenticatedUser(AuthenticatedUser.MethodEnum.TOKEN))
            .toProviderList()

    override suspend fun listProviders(
        includeDemoProviders: Boolean
    ): List<Provider> =
        api
            .list(
                body = AuthenticatedUser(AuthenticatedUser.MethodEnum.TOKEN),
                capability = null,
                includeTestProviders = includeDemoProviders,
                excludeNonTestProviders = false,
                name = null
            )
            .toProviderList()
}