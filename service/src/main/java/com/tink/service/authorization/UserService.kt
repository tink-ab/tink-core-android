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
