package com.tink.service.user

import com.tink.model.user.UserProfile
import com.tink.rest.apis.UserApi
import javax.inject.Inject

interface UserProfileService {
    suspend fun getProfile(): UserProfile
}

class UserProfileServiceImpl @Inject constructor(val api: UserApi) : UserProfileService {
    override suspend fun getProfile() = api.getUser().toUserProfile()
}
