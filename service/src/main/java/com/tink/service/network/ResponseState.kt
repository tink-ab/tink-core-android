package com.tink.service.network

sealed class ResponseState<out T>

object LoadingState : ResponseState<Nothing>()

data class SuccessState<T>(val data: T) : ResponseState<T>()

data class ErrorState<T>(val errorMessage: String? = null) : ResponseState<T>() {
    constructor(throwable: Throwable) : this(throwable.message)
}

fun <T, R> ResponseState<T>.map(f: (T) -> R): ResponseState<R> =
    when (this) {
        is SuccessState -> SuccessState(f(data))
        is LoadingState -> LoadingState
        is ErrorState -> ErrorState(errorMessage)
    }
