package com.tink.service.di

import com.tink.service.account.AccountService
import com.tink.service.account.AccountServiceImpl
import com.tink.service.authorization.UserService
import com.tink.service.authorization.UserServiceImpl
import com.tink.service.consent.ConsentService
import com.tink.service.consent.ConsentServiceImpl
import com.tink.service.credentials.CredentialsService
import com.tink.service.credentials.CredentialsServiceImpl
import com.tink.service.provider.ProviderService
import com.tink.service.provider.ProviderServiceImpl
import com.tink.service.transfer.TransferService
import com.tink.service.transfer.TransferServiceImpl
import dagger.Binds
import dagger.Module

@Module(includes = [RetrofitModule::class, CoroutineModule::class])
internal abstract class ServiceModule {

    @Binds
    abstract fun providerService(implementation: ProviderServiceImpl): ProviderService

    @Binds
    abstract fun credentialsService(implementation: CredentialsServiceImpl): CredentialsService

    @Binds
    internal abstract fun authorizationService(implementation: UserServiceImpl): UserService

    @Binds
    internal abstract fun consentService(implementation: ConsentServiceImpl): ConsentService

    @Binds
    internal abstract fun accountService(implementation: AccountServiceImpl): AccountService

    @Binds
    internal abstract fun transferService(implementation: TransferServiceImpl): TransferService
}
