package com.tink.service.misc

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime

internal fun Long?.toInstant() = Instant.ofEpochMilli(this ?: 0)

/**
 * Converts an instance from the given time zone id to an instant in UTC time.
 */
internal fun Long?.toInstantFromZone(originalTimeZoneId: String): Instant {
    val instantInOriginalTime = Instant.ofEpochMilli(this ?: 0)
    val originalLocalDateTime = LocalDateTime
        .ofInstant(instantInOriginalTime, ZoneId.of(originalTimeZoneId))
    return ZonedDateTime.of(originalLocalDateTime, ZoneId.of("UTC")).toInstant()
}
