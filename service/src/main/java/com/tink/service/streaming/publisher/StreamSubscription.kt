package com.tink.service.streaming.publisher

interface StreamSubscription {
    fun unsubscribe()
}

interface Stream<T> {
    fun subscribe(observer: StreamObserver<T>): StreamSubscription
}

interface StreamObserver<T> {
    fun onNext(value: T) {}
    fun onError(error: Throwable) {}
    fun onCompleted() {}
}
