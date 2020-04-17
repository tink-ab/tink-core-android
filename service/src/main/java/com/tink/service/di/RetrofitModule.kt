package com.tink.service.di

import com.tink.service.access.AccessApi
import com.tink.rest.apis.CredentialsApi
import com.tink.rest.apis.ProviderApi
import com.tink.rest.apis.OAuthApi
import com.tink.rest.tools.GeneratedCodeConverters
import com.tink.service.network.TinkConfiguration
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

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
    internal fun provideUserRetrofitService(
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
}
