package com.tink.core.provider

import com.tink.core.TinkScope
import com.tink.core.coroutines.launchForResult
import com.tink.model.provider.Provider
import com.tink.service.handler.ResultHandler
import com.tink.service.provider.ProviderFilter
import com.tink.service.provider.ProviderService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Inject

/**
 * Repository for retrieving [Provider]s
 *
 * @constructor Create a new repository instance from a [ProviderService].
 * This is usually done inside the TinkLink framework and it should normally not be necessary to create your own instance.
 */
@TinkScope
class ProviderRepository @Inject constructor(private val service: ProviderService) {

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    /**
     * List all providers on the current market. The result will already be filtered to only contain
     * providers that are [enabled][Provider.Status.ENABLED]
     * @param handler the [ResultHandler] for processing error and success callbacks
     * @param filter An optional filter that can be passed to modify the resulting list of providers
     */
    @JvmOverloads
    fun listProviders(
        handler: ResultHandler<List<Provider>>,
        filter: ProviderFilter? = null
    ) {
        scope.launchForResult(handler) {
            service.listProviders(filter)
        }
    }

    /**
     * Get the provider with the specified [name][Provider.name]. `null` will be passed to the
     * [resultHandler] in case no provider with this name could be found.
     */
    fun getProvider(providerName: String, resultHandler: ResultHandler<Provider?>) {
        scope.launchForResult(resultHandler) {
            service.getProvider(providerName)
        }
    }
}
