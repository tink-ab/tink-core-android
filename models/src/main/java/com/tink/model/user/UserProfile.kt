package com.tink.model.user

import android.os.Parcelable
import com.tink.model.authentication.AuthenticationMethod
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserProfile(
    val username: String,
    val locale: String,
    val market: String,
    val timeZone: String,
    val currency: String,
    val authorizedLoginMethods: Set<AuthenticationMethod>,
    val availableAuthenticationMethods: Set<AuthenticationMethod>
) : Parcelable {
    fun hasPassword(): Boolean {
        return authorizedLoginMethods
            .contains(AuthenticationMethod.EMAIL_AND_PASSWORD)
    }
}
