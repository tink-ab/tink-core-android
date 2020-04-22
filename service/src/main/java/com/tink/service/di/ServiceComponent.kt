package com.tink.service.di

import android.content.Context
import com.tink.service.authentication.UserEventBus
import com.tink.service.authorization.UserService
import com.tink.service.consent.ConsentService
import com.tink.service.credentials.CredentialsService
import com.tink.service.network.NetworkModule
import com.tink.service.network.TinkConfiguration
import com.tink.service.provider.ProviderService
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope

@Scope
annotation class ServiceScope

@Component(modules = [NetworkModule::class, ServiceModule::class])
@ServiceScope
abstract class ServiceComponent {

    abstract val providerService: ProviderService

    abstract val credentialsService: CredentialsService

    abstract val userService: UserService

    abstract val consentService: ConsentService

    abstract val tinkConfiguration: TinkConfiguration

    abstract val userEventBus: UserEventBus

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun tinkConfiguration(tinkConfiguration: TinkConfiguration): Builder

        @BindsInstance
        fun applicationContext(applicationContext: Context): Builder

        fun build(): ServiceComponent
    }
}
