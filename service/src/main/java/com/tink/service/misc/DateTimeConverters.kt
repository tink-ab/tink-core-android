package com.tink.service.misc

import com.google.protobuf.Timestamp
import org.threeten.bp.Instant

internal fun Timestamp.toInstant() = Instant.ofEpochSecond(seconds, nanos.toLong())

internal fun Long?.toInstant() = Instant.ofEpochMilli(this ?: 0)
