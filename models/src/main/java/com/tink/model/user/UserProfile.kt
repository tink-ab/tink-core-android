package com.tink.model.user

import com.tink.model.authentication.AuthenticationMethod

data class UserProfile(
    val username: String,
    val locale: String,
    val market: String,
    val timeZone: String,
    val currency: String,
    val authorizedLoginMethods: Set<AuthenticationMethod>,
    val availableAuthenticationMethods: Set<AuthenticationMethod>
) {
    fun hasPassword(): Boolean {
        return authorizedLoginMethods
            .contains(AuthenticationMethod.EMAIL_AND_PASSWORD)
    }
}
