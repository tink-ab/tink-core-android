package com.tink.service.account

import com.tink.model.account.Account
import com.tink.rest.apis.AccountApi
import com.tink.service.observer.ChangeObserver
import javax.inject.Inject

interface AccountService {
    fun subscribe(listener: ChangeObserver<List<Account>>)
    fun unsubscribe(listener: ChangeObserver<List<Account>>)
    suspend fun listAccounts(): List<Account>
    suspend fun update(descriptor: UpdateAccountDescriptor): Account
}

class AccountServiceImpl @Inject constructor(
    private val api: AccountApi
) : AccountService {
    override fun subscribe(listener: ChangeObserver<List<Account>>) {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun unsubscribe(listener: ChangeObserver<List<Account>>) {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun listAccounts(): List<Account> =
        api.listAccounts().accounts?.map { it.toCoreModel() } ?: listOf()

    override suspend fun update(descriptor: UpdateAccountDescriptor): Account =
        api.update(descriptor.id, descriptor.toRequest()).toCoreModel()
}
