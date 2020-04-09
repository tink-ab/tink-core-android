package com.tink.service.authorization

import com.tink.service.authentication.UserEventBus
import com.tink.service.authentication.user.User
import com.tink.service.di.ServiceScope
import com.tink.service.handler.ResultHandler
import com.tink.service.handler.toStreamObserver
import com.tink.service.network.TinkConfiguration
import io.grpc.Channel
import se.tink.grpc.v1.rpc.CreateAnonymousRequest
import se.tink.grpc.v1.services.UserServiceGrpc
import javax.inject.Inject

@ServiceScope
internal class UserServiceImpl @Inject constructor(
    private val tinkConfiguration: TinkConfiguration,
    private val retrofitService: UserRetrofitService,
    channel: Channel,
    userEventBus: UserEventBus
) : UserService {

    private val userStub = UserServiceGrpc.newStub(channel)
    private var user: User? = null

    init {
        userEventBus.subscribe { user = it }
    }

    override suspend fun authorize(scopes: Set<Scope>) =
        retrofitService.authorize(
            UserRetrofitService.AuthorizationRequest(
                tinkConfiguration.oAuthClientId,
                tinkConfiguration.redirectUri.toString(),
                scopes.joinToString(",")
            )
        ).authorizationCode

    override suspend fun authenticate(authenticationCode: String) =
        retrofitService.authenticate(UserRetrofitService.AuthenticationRequest(authenticationCode)).accessToken

    override fun createAnonymousUser(
        arguments: UserCreationDescriptor,
        resultHandler: ResultHandler<String>
    ) {
        CreateAnonymousRequest
            .newBuilder()
            .setMarket(arguments.market)
            .setLocale(arguments.locale)
            .build()
            .also { request ->
                userStub.createAnonymous(
                    request,
                    ResultHandler(
                        resultHandler.onSuccess,
                        resultHandler.onError
                    ).toStreamObserver { it.accessToken }
                )
            }
    }
}
