package com.tink.core

import android.content.Context
import com.tink.core.provider.ProviderRepository
import com.tink.model.user.User
import com.tink.service.account.AccountService
import com.tink.service.authentication.UserEventBus
import com.tink.service.authorization.UserService
import com.tink.service.budget.BudgetService
import com.tink.service.category.CategoryService
import com.tink.service.consent.ConsentService
import com.tink.service.credentials.CredentialsService
import com.tink.service.di.DaggerServiceComponent
import com.tink.service.di.ServiceComponent
import com.tink.service.insight.InsightService
import com.tink.service.network.TinkConfiguration
import com.tink.service.statistics.StatisticsService
import com.tink.service.time.PeriodService
import com.tink.service.transaction.TransactionService
import com.tink.service.transfer.TransferService
import com.tink.service.user.UserProfileService
import dagger.Component
import javax.inject.Scope
import kotlin.jvm.Throws

object Tink {

    private var component: TinkComponent? = null

    private var currentUser: User? = null

    private var tinkConfiguration: TinkConfiguration? = null

    private var isInitialized = false

    /**
     * Initializes Tink in the application and should be called before the usage of Tink components.
     * This should only be called once.
     *
     * @throws IllegalStateException Thrown if this method is called more than once.
     */
    @JvmStatic
    @Throws(IllegalStateException::class)
    fun init(config: TinkConfiguration, context: Context) {
        if (isInitialized) {
            throw IllegalStateException("Tink has already been initialized, you cannot call Tink.init more than once")
        }

        val serviceComponent = DaggerServiceComponent
            .builder()
            .tinkConfiguration(config)
            .applicationContext(context.applicationContext)
            .build()

        tinkConfiguration = config

        component = DaggerTinkComponent
            .builder()
            .serviceComponent(serviceComponent)
            .build()

        isInitialized = true
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
    fun getConfiguration(): TinkConfiguration? {
        return tinkConfiguration
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

    abstract val providerRepository: ProviderRepository

    abstract val userService: UserService

    abstract val userProfileService: UserProfileService

    abstract val consentService: ConsentService

    abstract val accountService: AccountService

    abstract val transferService: TransferService

    abstract val periodService: PeriodService

    abstract val statisticsService: StatisticsService

    abstract val transactionService: TransactionService

    abstract val insightService: InsightService

    abstract val budgetService: BudgetService

    abstract val categoryService: CategoryService

    abstract val tinkConfiguration: TinkConfiguration

    abstract val userEventBus: UserEventBus

    @Component.Builder
    internal interface Builder {

        fun serviceComponent(serviceComponent: ServiceComponent): Builder

        fun build(): TinkComponent
    }
}
