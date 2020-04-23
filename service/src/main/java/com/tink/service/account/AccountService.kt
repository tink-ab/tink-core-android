package com.tink.service.account

import com.tink.model.account.Account
import com.tink.service.handler.ResultHandler
import com.tink.service.observer.ChangeObserver
import javax.inject.Inject

interface AccountService {
    fun subscribe(listener: ChangeObserver<List<Account>>)
    fun unsubscribe(listener: ChangeObserver<List<Account>>)
    suspend fun listAccounts(): List<Account>
    suspend fun update(account: UpdateAccountDescriptor): Account
}

class AccountServiceImpl @Inject constructor() : AccountService {
    override fun subscribe(listener: ChangeObserver<List<Account>>) {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun unsubscribe(listener: ChangeObserver<List<Account>>) {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun listAccounts(): List<Account> {
        TODO("Not yet implemented")
    }

    override suspend fun update(account: UpdateAccountDescriptor): Account {
        TODO("Not yet implemented")
    }
}
