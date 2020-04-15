/**
 * NOTE: This class is auto generated by the Swagger Gradle Codegen for the following API: Tink API
 *
 * More info on this tool is available on https://github.com/Yelp/swagger-gradle-codegen
 */

package com.tink.service.generated.apis

import com.tink.service.generated.models.AuthenticatedUser
import com.tink.service.generated.models.AuthenticationContext
import com.tink.service.generated.models.ProviderListResponse
import com.tink.service.generated.models.ProviderMarketListResponse
import retrofit2.http.GET
import retrofit2.http.Headers

@JvmSuppressWildcards
interface ProviderApi {
    /**
     * List providers
     * Lists all providers available for a authenticated user.
     * The endpoint is owned by defaultname service owner
     * @param body (optional)
     * @param capability Use the capability to only list providers with a specific capability. If no capability the provider response will not be filtered on capability. (optional)
     * @param includeTestProviders Defaults to false. If set to &#x60;true&#x60;, Providers of &#x60;TEST&#x60; type will be added in the response list. (optional)
     * @param excludeNonTestProviders Defaults to false. If set to &#x60;true&#x60;, Providers of type different than &#x60;TEST&#x60; will be removed from the response list. (optional)
     * @param name Gets a specific provider from the name. If this query parameter is used, only one or no providers will be returned. (optional)
     */
    @Headers(
        "X-Operation-ID: list"
    )
    @GET("/api/v1/providers")
    suspend fun list(
        @retrofit2.http.Query("capability") capability: String?,
        @retrofit2.http.Query("includeTestProviders") includeTestProviders: Boolean?,
        @retrofit2.http.Query("excludeNonTestProviders") excludeNonTestProviders: Boolean?,
        @retrofit2.http.Query("name") name: String?
    ): ProviderListResponse

    /**
     * List providers for a market
     * Lists all providers on a specified market. Your authentication affects the list of providers you get: When authenticated, the result will only contain providers available for that user. When unauthenticated and without passing The OAuth2 Client ID header, you get all providers made available for the entire environment. If you are unauthenticated but you include The OAuth2 Client ID header, you will get providers available for the app with the given client id.
     * The endpoint is owned by defaultname service owner
     * @param market The ISO 3166-1 alpha-2 market code. (required)
     * @param body (optional)
     * @param xTinkOAuthClientID The OAuth2 Client ID (optional)
     * @param acceptLanguage Language to translate to. (optional)
     * @param includeTestProviders Defaults to false. If set to &#x60;true&#x60;, Providers of &#x60;TEST&#x60; type will be added in the response list. (optional)
     * @param excludeNonTestProviders Defaults to false. If set to &#x60;true&#x60;, Providers of type different than &#x60;TEST&#x60; will be removed from the response list. (optional)
     * @param capability Use the capability to only list providers with a specific capability. If no capability the provider response will not be filtered on capability. (optional)
     */
    @Headers(
        "X-Operation-ID: listByMarket"
    )
    @GET("/api/v1/providers/{market}")
    suspend fun listByMarket(
        @retrofit2.http.Path("market") market: String,
        @retrofit2.http.Header("X-Tink-OAuth-Client-ID") xTinkOAuthClientID: String?,
        @retrofit2.http.Header("Accept-Language") acceptLanguage: String?,
        @retrofit2.http.Query("includeTestProviders") includeTestProviders: Boolean?,
        @retrofit2.http.Query("excludeNonTestProviders") excludeNonTestProviders: Boolean?,
        @retrofit2.http.Query("capability") capability: String?
    ): ProviderListResponse

    /**
     * List markets
     * Lists all markets where there are providers available.
     * The endpoint is owned by defaultname service owner
     * @param body (optional)
     * @param xTinkOAuthClientID The OAuth2 Client ID (optional)
     */
    @Headers(
        "X-Operation-ID: listMarkets"
    )
    @GET("/api/v1/providers/markets")
    suspend fun listMarkets(
        @retrofit2.http.Header("X-Tink-OAuth-Client-ID") xTinkOAuthClientID: String?
    ): ProviderMarketListResponse

    /**
     * Suggest providers for user.
     *
     * The endpoint is owned by defaultname service owner
     */
    @Headers(
        "X-Operation-ID: suggest"
    )
    @GET("/api/v1/providers/suggest")
    suspend fun suggest(): ProviderListResponse
}
