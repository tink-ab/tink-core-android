package com.tink.service.account

import com.tink.model.account.Account
import com.tink.rest.apis.AccountApi
import javax.inject.Inject

interface AccountService {
    suspend fun listAccounts(): List<Account>
    suspend fun update(descriptor: UpdateAccountDescriptor): Account
}

class AccountServiceImpl @Inject constructor(
    private val api: AccountApi
) : AccountService {

    override suspend fun listAccounts(): List<Account> =
        api.listAccounts().accounts?.map { it.toCoreModel() } ?: listOf()

    override suspend fun update(descriptor: UpdateAccountDescriptor): Account =
        api.update(descriptor.id, descriptor.toRequest()).toCoreModel()
}
