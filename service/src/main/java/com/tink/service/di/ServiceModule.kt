package com.tink.service.di

import com.tink.service.authentication.AuthenticationService
import com.tink.service.authentication.AuthenticationServiceImpl
import com.tink.service.authorization.UserService
import com.tink.service.authorization.UserServiceImpl
import com.tink.service.consent.ConsentService
import com.tink.service.consent.ConsentServiceImpl
import com.tink.service.credentials.CredentialsService
import com.tink.service.credentials.CredentialsServiceImpl
import com.tink.service.di.RetrofitModule
import com.tink.service.provider.ProviderService
import com.tink.service.provider.ProviderServiceImpl
import dagger.Binds
import dagger.Module

@Module(includes = [RetrofitModule::class])
abstract class ServiceModule {

    @Binds
    abstract fun providerService(implementation: ProviderServiceImpl): ProviderService

    @Binds
    abstract fun credentialsService(implementation: CredentialsServiceImpl): CredentialsService

    @Binds
    internal abstract fun authenticationService(implementation: AuthenticationServiceImpl): AuthenticationService

    @Binds
    internal abstract fun authorizationService(implementation: UserServiceImpl): UserService

    @Binds
    internal abstract fun consentService(implementation: ConsentServiceImpl): ConsentService
}
