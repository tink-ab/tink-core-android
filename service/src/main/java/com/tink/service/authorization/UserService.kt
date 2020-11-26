package com.tink.service.authorization

import com.tink.model.user.Scope
import com.tink.model.user.UserInfo

interface UserService {
    suspend fun authorize(scopes: Set<Scope>): String
    suspend fun authenticate(authenticationCode: String): String
    suspend fun createAnonymousUser(arguments: UserCreationDescriptor): String
    suspend fun getUserInfo(): UserInfo
}

data class UserCreationDescriptor(
    val market: String,
    val locale: String
)
