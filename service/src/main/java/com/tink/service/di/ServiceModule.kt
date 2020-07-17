package com.tink.service.di

import com.tink.service.account.AccountService
import com.tink.service.account.AccountServiceImpl
import com.tink.service.authorization.UserService
import com.tink.service.authorization.UserServiceImpl
import com.tink.service.budget.BudgetService
import com.tink.service.budget.BudgetServiceImpl
import com.tink.service.category.CategoryService
import com.tink.service.category.CategoryServiceImpl
import com.tink.service.consent.ConsentService
import com.tink.service.consent.ConsentServiceImpl
import com.tink.service.credentials.CredentialsService
import com.tink.service.credentials.CredentialsServiceImpl
import com.tink.service.insight.InsightService
import com.tink.service.insight.InsightServiceImpl
import com.tink.service.provider.ProviderService
import com.tink.service.provider.ProviderServiceImpl
import com.tink.service.statistics.StatisticsService
import com.tink.service.statistics.StatisticsServiceImpl
import com.tink.service.time.PeriodService
import com.tink.service.time.PeriodServiceImpl
import com.tink.service.transaction.TransactionService
import com.tink.service.transaction.TransactionServiceImpl
import com.tink.service.user.UserProfileService
import com.tink.service.user.UserProfileServiceImpl
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
    internal abstract fun budgetService(implementation: BudgetServiceImpl): BudgetService

    @Binds
    internal abstract fun categoryService(implementation: CategoryServiceImpl): CategoryService

    @Binds
    internal abstract fun insightService(implementation: InsightServiceImpl): InsightService

    @Binds
    internal abstract fun statisticsService(implementation: StatisticsServiceImpl): StatisticsService

    @Binds
    internal abstract fun transactionService(implementation: TransactionServiceImpl): TransactionService

    @Binds
    internal abstract fun userProfileService(implementation: UserProfileServiceImpl): UserProfileService

    @Binds
    internal abstract fun accountService(implementation: AccountServiceImpl): AccountService

    @Binds
    internal abstract fun transferService(implementation: TransferServiceImpl): TransferService

    @Binds
    internal abstract fun periodService(implementation: PeriodServiceImpl): PeriodService
}
