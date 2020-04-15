package com.tink.service.misc

import org.threeten.bp.Instant

internal fun Long?.toInstant() = Instant.ofEpochMilli(this ?: 0)
