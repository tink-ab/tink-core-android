package com.tink.service.misc

import java.time.Instant

internal fun Long?.toInstant() = Instant.ofEpochMilli(this ?: 0)
