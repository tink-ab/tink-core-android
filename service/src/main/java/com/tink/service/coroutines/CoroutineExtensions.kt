package com.tink.service.coroutines

import com.tink.service.handler.ResultHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

internal fun <T> CoroutineScope.launchForResult(
    resultHandler: ResultHandler<T>,
    action: suspend () -> T
) {
    launch {
        try {
            val result = action()
            resultHandler.onSuccess(result)
        } catch (error: Throwable) {
            resultHandler.onError(error)
        }
    }
}
