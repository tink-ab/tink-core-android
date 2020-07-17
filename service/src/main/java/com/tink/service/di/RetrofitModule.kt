package com.tink.service.di

import com.tink.rest.apis.AccessApi
import com.tink.rest.apis.AccountApi
import com.tink.rest.apis.BeneficiaryApi
import com.tink.rest.apis.CalendarApi
import com.tink.rest.apis.CredentialsApi
import com.tink.rest.apis.ProviderApi
import com.tink.rest.apis.OAuthApi
import com.tink.rest.apis.StatisticsApi
import com.tink.rest.apis.TransferApi
import com.tink.rest.apis.UserApi
import com.tink.rest.tools.GeneratedCodeConverters
import com.tink.service.authorization.UserService
import com.tink.service.network.TinkConfiguration
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.create

@Module
internal class RetrofitModule {

    @Provides
    @ServiceScope
    internal fun provideRetrofit(client: OkHttpClient, config: TinkConfiguration): Retrofit =
        Retrofit.Builder()
            .baseUrl(config.environment.restUrl)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GeneratedCodeConverters.converterFactory())
            .build()

    @Provides
    @ServiceScope
    internal fun provideAccessRetrofitService(
        retrofit: Retrofit
    ): AccessApi = retrofit.create(AccessApi::class.java)

    @Provides
    @ServiceScope
    internal fun provideCredentialsRetrofitService(
        retrofit: Retrofit
    ): CredentialsApi = retrofit.create(CredentialsApi::class.java)

    @Provides
    @ServiceScope
    internal fun provideOAuthRetrofitService(
        retrofit: Retrofit
    ): OAuthApi = retrofit.create(OAuthApi::class.java)

    @Provides
    @ServiceScope
    internal fun provideProviderRetrofitService(
        retrofit: Retrofit
    ): ProviderApi = retrofit.create(ProviderApi::class.java)

    @Provides
    @ServiceScope
    internal fun providerAccountRetrofitService(
        retrofit: Retrofit
    ): AccountApi = retrofit.create(AccountApi::class.java)

    @Provides
    @ServiceScope
    internal fun provideTransferRetrofitService(
        retrofit: Retrofit
    ): TransferApi = retrofit.create(TransferApi::class.java)

    @Provides
    @ServiceScope
    internal fun provideBeneficiaryRetrofitService(
        retrofit: Retrofit
    ): BeneficiaryApi = retrofit.create(BeneficiaryApi::class.java)

    @Provides
    @ServiceScope
    internal fun provideUserRetrofitService(
        retrofit: Retrofit
    ): UserApi = retrofit.create(UserApi::class.java)

    @Provides
    @ServiceScope
    internal fun provideCalendarRetrofitService(
        retrofit: Retrofit
    ): CalendarApi = retrofit.create(CalendarApi::class.java)

    @Provides
    @ServiceScope
    internal fun provideStatisticsRetrofitService(
        retrofit: Retrofit
    ): StatisticsApi = retrofit.create(StatisticsApi::class.java)
}
