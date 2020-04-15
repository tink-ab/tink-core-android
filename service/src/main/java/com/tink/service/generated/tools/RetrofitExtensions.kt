package com.tink.service.generated.tools

import retrofit2.HttpException
import retrofit2.Response

fun Response<Unit>.unwrap() {
    if (!isSuccessful) throw HttpException(this)
}
