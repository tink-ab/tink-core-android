package com.tink.service.user

import com.tink.model.user.UserProfile
import com.tink.rest.models.User as UserDto

fun UserDto.toUserProfile() =
    UserProfile(
        username = username ?: "",
        locale = profile.locale,
        market = profile.market,
        timeZone = profile.timeZone,
        currency = profile.currency,
        // TODO
        authorizedLoginMethods = setOf(),
        availableAuthenticationMethods = setOf()
    )
