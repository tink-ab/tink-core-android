package com.tink.service.authorization

import com.tink.service.handler.ResultHandler

interface UserService {
    suspend fun authorize(scopes: Set<Scope>): String
    suspend fun authenticate(authenticationCode: String): String
    fun createAnonymousUser(arguments: UserCreationDescriptor, resultHandler: ResultHandler<String>)
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

data class UserCreationDescriptor(
    val market: String,
    val locale: String
)
