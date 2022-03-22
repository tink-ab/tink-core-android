package com.tink.service.di

import android.content.Context
import com.tink.service.account.AccountService
import com.tink.service.authentication.UserEventBus
import com.tink.service.authorization.UserService
import com.tink.service.budget.BudgetService
import com.tink.service.category.CategoryService
import com.tink.service.consent.ConsentService
import com.tink.service.credentials.CredentialsService
import com.tink.service.insight.InsightService
import com.tink.service.network.NetworkModule
import com.tink.service.network.TinkConfiguration
import com.tink.service.provider.ProviderService
import com.tink.service.statistics.StatisticsService
import com.tink.service.time.PeriodService
import com.tink.service.transaction.TransactionService
import com.tink.service.transfer.TransferService
import com.tink.service.user.UserProfileService
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

    abstract val accountService: AccountService

    abstract val transferService: TransferService

    abstract val transactionService: TransactionService
    abstract val categoryService: CategoryService
    abstract val statisticsService: StatisticsService
    abstract val budgetService: BudgetService
    abstract val insightService: InsightService
    abstract val userProfileService: UserProfileService
    abstract val periodService: PeriodService

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
