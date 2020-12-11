package com.tink.service.authorization

import com.tink.model.user.UserInfo
import com.tink.model.user.UserProfile
import com.tink.service.misc.toInstant
import com.tink.rest.models.User as UserDto
import com.tink.rest.models.UserProfile as UserProfileDto

internal fun UserDto.toCoreModel(): UserInfo =
    UserInfo(
        created = created.toInstant(),
        id = id,
        appId = appId,
        profile = profile.toCoreModel(),
        flags = flags ?: emptyList(),
        username = username,
        nationalId = nationalId
    )

internal fun UserProfileDto.toCoreModel(): UserProfile =
    UserProfile(
        locale = locale,
        market = market,
        timeZone = timeZone,
        currency = currency,
        periodMode = periodMode.toCoreModel(periodAdjustedDay)
    )

internal fun UserProfileDto.PeriodModeEnum.toCoreModel(periodAdjustedDay: Int) =
    when (this) {
        UserProfileDto.PeriodModeEnum.MONTHLY -> UserProfile.PeriodMode.Monthly
        UserProfileDto.PeriodModeEnum.MONTHLY_ADJUSTED -> UserProfile.PeriodMode.MonthlyAdjusted(periodAdjustedDay)
    }
