package com.tink.service.transaction

import com.tink.service.handler.ResultHandler

interface Pageable {
    fun next(resultHandler: ResultHandler<Unit>)
    fun hasMore(): Boolean
}
