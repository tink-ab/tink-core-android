package com.tink.service.streaming

import com.tink.service.streaming.publisher.Stream
import com.tink.service.streaming.publisher.StreamObserver
import com.tink.service.streaming.publisher.StreamSubscription
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

private const val POLLING_INTERVAL = 2_000L

class PollingHandler<T>(private val pollingAction: suspend (StreamObserver<T>) -> Unit) : Stream<T> {

    override fun subscribe(observer: StreamObserver<T>): StreamSubscription {
        val job = Job()
        val exceptionHandler = CoroutineExceptionHandler { _, t -> observer.onError(t) }

        CoroutineScope(Dispatchers.IO + job + exceptionHandler).launch {
            while (true) {
                Timber.d("Polling in job: ${coroutineContext[Job]}")
                pollingAction(observer)
                delay(POLLING_INTERVAL)
            }
        }

        return object : StreamSubscription {
            override fun unsubscribe() {
                Timber.d("Cancelling job")
                observer.onCompleted()
                job.cancel()
            }
        }
    }
}
