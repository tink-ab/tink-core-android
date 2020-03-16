package com.tink.service.user

import com.tink.model.user.UserProfile
import com.tink.service.handler.ResultHandler
import javax.inject.Inject

interface UserProfileService {
    fun getProfile(handler: ResultHandler<UserProfile>)
}

class UserProfileServiceImpl @Inject constructor() : UserProfileService {
    override fun getProfile(handler: ResultHandler<UserProfile>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}