package com.tink.service.authorization

import com.tink.model.user.Scope
import com.tink.service.access.AccessApi
import com.tink.service.access.AuthenticationRequest
import com.tink.service.access.AuthorizationRequest
import com.tink.service.access.CreateAnonymousUserRequest
import com.tink.service.di.ServiceScope
import com.tink.service.network.TinkConfiguration
import javax.inject.Inject

@ServiceScope
internal class UserServiceImpl @Inject constructor(
    private val tinkConfiguration: TinkConfiguration,
    private val api: AccessApi
) : UserService {

    override suspend fun authorize(scopes: Set<Scope>) =
        api.authorize(
            AuthorizationRequest(
                tinkConfiguration.oAuthClientId,
                tinkConfiguration.redirectUri.toString(),
                scopes.joinToString(",")
            )
        ).authorizationCode

    override suspend fun authenticate(authenticationCode: String) =
        api.authenticate(AuthenticationRequest(authenticationCode)).accessToken

    override suspend fun createAnonymousUser(arguments: UserCreationDescriptor) =
        api.createAnonymousUser(
            CreateAnonymousUserRequest(
                arguments.market,
                arguments.locale
            )
        ).accessToken
}
