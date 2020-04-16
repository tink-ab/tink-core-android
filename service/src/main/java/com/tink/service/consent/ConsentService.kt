package com.tink.service.consent

import com.tink.model.consent.OAuthClientDescription
import com.tink.model.user.Scope
import com.tink.service.di.ServiceScope
import com.tink.service.generated.apis.DescribeOAuth2ClientRequest
import com.tink.service.generated.apis.OAuthApi
import com.tink.service.network.TinkConfiguration
import javax.inject.Inject

interface ConsentService {
    suspend fun describeClient(scopes: Set<Scope>): OAuthClientDescription
}

@ServiceScope
class ConsentServiceImpl @Inject constructor(
    private val api: OAuthApi,
    private val tinkConfiguration: TinkConfiguration
) : ConsentService {

    override suspend fun describeClient(scopes: Set<Scope>) =
        api.describe(
            DescribeOAuth2ClientRequest(
                clientId = tinkConfiguration.oAuthClientId,
                redirectUri = tinkConfiguration.redirectUri.toString(),
                scopes = scopes.joinToString(",")
            )
        ).toOAuthClientDescription()
}
