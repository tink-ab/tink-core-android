package com.tink.rest.tools

import retrofit2.HttpException
import retrofit2.Response

fun Response<Unit>.unwrap() {
    if (!isSuccessful) throw HttpException(this)
}
