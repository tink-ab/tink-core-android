package com.tink.core

import android.content.Context
import com.tink.core.provider.ProviderRepository
import com.tink.model.user.User
import com.tink.service.account.AccountService
import com.tink.service.authentication.UserEventBus
import com.tink.service.authorization.UserService
import com.tink.service.consent.ConsentService
import com.tink.service.credentials.CredentialsService
import com.tink.service.di.DaggerServiceComponent
import com.tink.service.di.ServiceComponent
import com.tink.service.network.TinkConfiguration
import com.tink.service.transfer.TransferService
import dagger.Component
import javax.inject.Scope

object Tink {

    private var component: TinkComponent? = null

    private var currentUser: User? = null

    @JvmStatic
    fun init(config: TinkConfiguration, context: Context) {
        val serviceComponent = DaggerServiceComponent
            .builder()
            .tinkConfiguration(config)
            .applicationContext(context.applicationContext)
            .build()

        component = DaggerTinkComponent
            .builder()
            .serviceComponent(serviceComponent)
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

@Scope
annotation class TinkScope

@Component(dependencies = [ServiceComponent::class])
@TinkScope
abstract class TinkComponent {

    abstract val credentialsService: CredentialsService

    abstract val userService: UserService

    abstract val consentService: ConsentService

    abstract val accountService: AccountService

    abstract val transferService: TransferService

    abstract val providerRepository: ProviderRepository

    abstract val tinkConfiguration: TinkConfiguration

    abstract val userEventBus: UserEventBus

    @Component.Builder
    internal interface Builder {

        fun serviceComponent(serviceComponent: ServiceComponent): Builder

        fun build(): TinkComponent
    }
}
