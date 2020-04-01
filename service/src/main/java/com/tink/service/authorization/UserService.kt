package com.tink.service.authorization

import android.annotation.SuppressLint
import com.tink.service.authentication.UserEventBus
import com.tink.service.authentication.user.Authorization
import com.tink.service.authentication.user.User
import com.tink.service.di.ServiceScope
import com.tink.service.handler.ResultHandler
import com.tink.service.network.TinkConfiguration
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

interface UserService {
    fun authorize(scopes: Set<Scope>, resultHandler: ResultHandler<String>)
    fun authenticate(authenticationCode: String, resultHandler: ResultHandler<String>)
}

@ServiceScope
internal class UserServiceImpl @Inject constructor(
    private val tinkConfiguration: TinkConfiguration,
    private val retrofitService: UserRetrofitService,
    userEventBus: UserEventBus
) : UserService {

    private var user: User? = null

    init {
        userEventBus.subscribe { user = it }
    }

    @SuppressLint("CheckResult")
    override fun authorize(
        scopes: Set<Scope>,
        resultHandler: ResultHandler<String>
    ) {

        val accessToken = requireNotNull(
            (user?.authorization as? Authorization.AccessToken)?.accessToken
        ) { "User token not set" }

        retrofitService
            .authorize(
                "Bearer $accessToken",
                UserRetrofitService.AuthorizationRequest(
                    tinkConfiguration.oAuthClientId,
                    tinkConfiguration.redirectUri.toString(),
                    scopes.joinToString(",")
                )
            )
            .map { it.authorizationCode }
            ?.subscribeOn(Schedulers.io())
            ?.subscribe(
                { resultHandler.onSuccess(it) },
                { resultHandler.onError(it) }
            )
    }

    @SuppressLint("CheckResult")
    override fun authenticate(authenticationCode: String, resultHandler: ResultHandler<String>) {
        retrofitService
            .authenticate(UserRetrofitService.AuthenticationRequest(authenticationCode))
            .map { it.accessToken }
            .subscribeOn(Schedulers.io())
            .subscribe(
                resultHandler.onSuccess,
                resultHandler.onError
            )
    }
}

sealed class Scope(private val scope: String) {
    object TransactionsRead : Scope("transactions:read")
    object AccountsRead : Scope("accounts:read")
    object UserRead : Scope("user:read")
    object CredentialsRead : Scope("credentials:read")
    object IdentityRead : Scope("identity:read")
    object InvestmentsRead : Scope("investments:read")
    object StatisticsRead : Scope("statistics:read")
    class CustomScope(scope: String) : Scope(scope)

    override fun toString(): String = scope
}
