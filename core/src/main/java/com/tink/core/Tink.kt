package com.tink.core

import android.content.Context
import com.tink.core.provider.ProviderRepository
import com.tink.service.ServiceModule
import com.tink.service.authentication.AccessTokenEventBus
import com.tink.service.authorization.UserService
import com.tink.service.credential.CredentialService
import com.tink.service.network.NetworkModule
import com.tink.service.network.TinkConfiguration
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

typealias AccessToken = String

object Tink {

    private var component: TinkComponent? = null

    fun init(config: TinkConfiguration, context: Context) {

        component = DaggerTinkComponent
            .builder()
            .applicationContext(context.applicationContext)
            .tinkConfiguration(config)
            .build()
    }

    fun setUser(accessToken: AccessToken) {
        requireComponent().accessTokenEventBus.postAccessToken(accessToken)
    }

    fun providerRepository(): ProviderRepository =
        requireComponent().providerRepository

    fun requireComponent() = checkNotNull(component) { "Tink is not initialized" }

}

@Component(modules = [NetworkModule::class, ServiceModule::class])
@Singleton
abstract class TinkComponent {

    abstract val providerRepository: ProviderRepository

    abstract val credentialService: CredentialService

    abstract val userService: UserService

    abstract val tinkConfiguration: TinkConfiguration

    abstract val accessTokenEventBus: AccessTokenEventBus

    @Component.Builder
    internal interface Builder {

        @BindsInstance
        fun tinkConfiguration(tinkConfiguration: TinkConfiguration): Builder

        @BindsInstance
        fun applicationContext(applicationContext: Context): Builder

        fun build(): TinkComponent
    }
}
