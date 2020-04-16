package com.tink.service.di

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named

const val SERVICE_DISPATCHER = "serviceDispatcher"

@Module
internal class CoroutineModule {

    @Provides
    @Named(SERVICE_DISPATCHER)
    fun provideServiceDispatcher(): CoroutineDispatcher = Dispatchers.IO
}
