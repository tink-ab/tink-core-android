package com.tink.service.authorization

import com.tink.model.user.Scope
import com.tink.service.di.ServiceScope
import com.tink.service.network.TinkConfiguration
import javax.inject.Inject

@ServiceScope
internal class UserServiceImpl @Inject constructor(
    private val tinkConfiguration: TinkConfiguration,
    private val retrofitService: UserRetrofitService
) : UserService {

    override suspend fun authorize(scopes: Set<Scope>) =
        retrofitService.authorize(
            AuthorizationRequest(
                tinkConfiguration.oAuthClientId,
                tinkConfiguration.redirectUri.toString(),
                scopes.joinToString(",")
            )
        ).authorizationCode

    override suspend fun authenticate(authenticationCode: String) =
        retrofitService.authenticate(AuthenticationRequest(authenticationCode)).accessToken

    override suspend fun createAnonymousUser(arguments: UserCreationDescriptor) =
        retrofitService.createAnonymousUser(
            CreateAnonymousUserRequest(
                arguments.market,
                arguments.locale
            )
        ).accessToken
}
