package com.tink.service.user

import com.tink.model.user.PeriodMode
import com.tink.model.user.UserProfile
import com.tink.rest.models.User as UserDto
import com.tink.rest.models.UserProfile as UserProfileDto
import com.tink.rest.models.UserProfile.PeriodModeEnum as PeriodModeDto

fun UserDto.toUserProfile() =
    UserProfile(
        username = username ?: "",
        locale = profile.locale,
        market = profile.market,
        timeZone = profile.timeZone,
        currency = profile.currency,
        periodMode = profile.toPeriodMode(),
        // TODO
        authorizedLoginMethods = setOf(),
        availableAuthenticationMethods = setOf()
    )

fun UserProfileDto.toPeriodMode() =
    when (periodMode) {
        PeriodModeDto.MONTHLY -> PeriodMode.Monthly
        PeriodModeDto.MONTHLY_ADJUSTED -> PeriodMode.MonthlyAdjusted(periodAdjustedDay)
    }
