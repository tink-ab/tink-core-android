package com.tink.service.authorization

interface UserService {
    suspend fun authorize(scopes: Set<Scope>): String
    suspend fun authenticate(authenticationCode: String): String
    suspend fun createAnonymousUser(arguments: UserCreationDescriptor): String
}

data class UserCreationDescriptor(
    val market: String,
    val locale: String
)
