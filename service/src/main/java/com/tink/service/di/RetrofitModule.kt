package com.tink.service.di

import com.tink.service.authorization.UserRetrofitService
import com.tink.service.network.TinkConfiguration
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetrofitModule {

    @Provides
    @Singleton
    internal fun provideRetrofit(client: OkHttpClient, config: TinkConfiguration): Retrofit =
        Retrofit.Builder()
            .baseUrl(config.environment.restUrl)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    internal fun provideUserRetrofitService(
        retrofit: Retrofit
    ): UserRetrofitService = retrofit.create(UserRetrofitService::class.java)
}