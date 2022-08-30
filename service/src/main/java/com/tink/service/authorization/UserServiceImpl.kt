package com.tink.service.authorization

import com.tink.model.user.Scope
import com.tink.rest.apis.AccessApi
import com.tink.rest.apis.AuthenticationRequest
import com.tink.rest.apis.AuthorizationRequest
import com.tink.rest.apis.CreateAnonymousUserRequest
import com.tink.rest.apis.UserApi
import com.tink.service.di.ServiceScope
import com.tink.service.network.TinkConfiguration
import javax.inject.Inject

@ServiceScope
internal class UserServiceImpl @Inject constructor(
    private val tinkConfiguration: TinkConfiguration,
    private val accessApi: AccessApi,
    private val userApi: UserApi
) : UserService {

    override suspend fun authorize(scopes: Set<Scope>) =
        accessApi.authorize(
            AuthorizationRequest(
                tinkConfiguration.oAuthClientId,
                tinkConfiguration.redirectUri.toString(),
                scopes.joinToString(",")
            )
        ).authorizationCode

    override suspend fun authenticate(authenticationCode: String) =
        accessApi.authenticate(
            AuthenticationRequest(
                tinkConfiguration.oAuthClientId,
                authenticationCode
            )
        ).accessToken

    override suspend fun createAnonymousUser(arguments: UserCreationDescriptor) =
        accessApi.createAnonymousUser(
            CreateAnonymousUserRequest(
                arguments.market,
                arguments.locale
            )
        ).accessToken

    override suspend fun getUserInfo() = userApi.getUser().toCoreModel()
}
