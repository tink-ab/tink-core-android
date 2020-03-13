package com.tink.core

import android.content.Context
import com.tink.core.provider.ProviderRepository
import com.tink.service.authentication.user.User
import com.tink.service.ServiceModule
import com.tink.service.authentication.UserEventBus
import com.tink.service.authorization.UserService
import com.tink.service.consent.ConsentService
import com.tink.service.credential.CredentialsService
import com.tink.service.network.NetworkModule
import com.tink.service.network.TinkConfiguration
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

object Tink {

    private var component: TinkComponent? = null

    private var currentUser: User? = null

    @JvmStatic
    fun init(config: TinkConfiguration, context: Context) {

        component = DaggerTinkComponent
            .builder()
            .applicationContext(context.applicationContext)
            .tinkConfiguration(config)
            .build()
    }

    @JvmStatic
    fun setUser(user: User) {
        currentUser = user
        requireComponent().userEventBus.postUser(user)
    }

    @JvmStatic
    fun getUser(): User? {
        return currentUser
    }

    @JvmStatic
    fun providerRepository(): ProviderRepository =
        requireComponent().providerRepository

    @JvmStatic
    fun requireComponent() = checkNotNull(component) { "Tink is not initialized" }
}

@Component(modules = [NetworkModule::class, ServiceModule::class])
@Singleton
abstract class TinkComponent {

    abstract val providerRepository: ProviderRepository

    abstract val credentialsService: CredentialsService

    abstract val userService: UserService

    abstract val consentService: ConsentService

    abstract val tinkConfiguration: TinkConfiguration

    abstract val userEventBus: UserEventBus

    @Component.Builder
    internal interface Builder {

        @BindsInstance
        fun tinkConfiguration(tinkConfiguration: TinkConfiguration): Builder

        @BindsInstance
        fun applicationContext(applicationContext: Context): Builder

        fun build(): TinkComponent
    }
}
