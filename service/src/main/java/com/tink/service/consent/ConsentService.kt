package com.tink.service.consent

import com.tink.service.authorization.Scope
import com.tink.service.handler.ResultHandler
import com.tink.service.handler.toStreamObserver
import com.tink.service.network.TinkConfiguration
import io.grpc.Channel
import se.tink.grpc.v1.rpc.DescribeOAuth2ClientRequest
import se.tink.grpc.v1.services.AuthenticationServiceGrpc
import javax.inject.Inject

interface ConsentService {
    fun scopeDescriptions(scopes: Set<Scope>, resultHandler: ResultHandler<List<ScopeDescription>>)
}

class ConsentServiceImpl @Inject constructor(
    channel: Channel,
    private val tinkConfiguration: TinkConfiguration
) : ConsentService {
    private val authServiceStub: AuthenticationServiceGrpc.AuthenticationServiceStub =
        AuthenticationServiceGrpc.newStub(channel)

    override fun scopeDescriptions(
        scopes: Set<Scope>,
        resultHandler: ResultHandler<List<ScopeDescription>>
    ) {
        val request = DescribeOAuth2ClientRequest.newBuilder()
            .addAllScopes(scopes.map { it.toString() })
            .setClientId(tinkConfiguration.oAuthClientId)
            .setRedirectUri(tinkConfiguration.redirectUri.toString())
            .build()

        authServiceStub.describeOAuth2Client(request, resultHandler.toStreamObserver { response ->
            response.scopesList.map { ScopeDescription(it.title, it.description) }
        })
    }
}

/**
 * A title and description explaining a specific [Scope].
 */
data class ScopeDescription(
    /** The short string of what access the scope is for. */
    val title: String,
    /** A string describing what user data the scope provides access to. */
    val description: String
)