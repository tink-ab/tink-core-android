package com.tink.service.account

import com.tink.model.account.Account
import com.tink.service.handler.ResultHandler

interface AccountService {

    fun update(descriptor: UpdateAccountDescriptor, handler: ResultHandler<Account>)
}
