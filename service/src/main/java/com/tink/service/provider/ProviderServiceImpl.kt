package com.tink.service.provider

import com.tink.model.provider.Provider
import com.tink.service.coroutines.launchForResult
import com.tink.service.di.ServiceScope
import com.tink.service.generated.apis.ProviderApi
import com.tink.service.generated.models.AuthenticatedUser
import com.tink.service.handler.ResultHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Inject

@ServiceScope
class ProviderServiceImpl @Inject constructor(
    private val api: ProviderApi
) : ProviderService {

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    override fun listSuggestions(handler: ResultHandler<List<Provider>>) {
        scope.launchForResult(handler) {
            api.suggest(body = AuthenticatedUser(AuthenticatedUser.MethodEnum.TOKEN))
                .toProviderList()
        }
    }

    override fun listProviders(
        handler: ResultHandler<List<Provider>>,
        includeDemoProviders: Boolean
    ) {
        scope.launchForResult(handler) {
            api
                .list(
                    body = AuthenticatedUser(AuthenticatedUser.MethodEnum.TOKEN),
                    capability = null,
                    includeTestProviders = false,
                    excludeNonTestProviders = false,
                    name = null
                )
                .toProviderList()
        }
    }
}