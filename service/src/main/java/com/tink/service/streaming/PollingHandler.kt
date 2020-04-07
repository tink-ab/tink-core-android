package com.tink.service.streaming

import com.tink.service.streaming.publisher.Stream
import com.tink.service.streaming.publisher.StreamObserver
import com.tink.service.streaming.publisher.StreamSubscription
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

private const val POLLING_INTERVAL = 2_000L

class PollingHandler<T>(private val pollingAction: suspend (StreamObserver<T>) -> Unit) : Stream<T> {

    private val job = Job()
    private val ioScope = CoroutineScope(Dispatchers.IO + job)

    override fun subscribe(observer: StreamObserver<T>): StreamSubscription {
        ioScope.launch {
            while (true) {
                try {
                    Timber.d("Polling in job: ${coroutineContext[Job]}")
                    pollingAction(observer)
                } catch (e: Exception) {
                    observer.onError(e)
                }
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