package com.tink.service.network

sealed class ResponseState<out T>

object LoadingState : ResponseState<Nothing>()

data class SuccessState<T>(val data: T) : ResponseState<T>()

data class ErrorState<T>(val errorMessage: String? = null) : ResponseState<T>() {
    constructor(throwable: Throwable) : this(throwable.message)
}