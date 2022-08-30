package com.tink.service.time

import com.tink.model.time.DayPeriod
import com.tink.model.time.MonthPeriod
import com.tink.model.time.Period
import com.tink.model.time.WeekPeriod
import com.tink.model.time.YearPeriod
import com.tink.service.misc.toInstantFromZone
import java.lang.IllegalArgumentException
import com.tink.rest.models.Period as PeriodDto

internal fun PeriodDto.toCoreModel(userTimeZoneId: String): Period {

    val start = startDate!!.toInstantFromZone(userTimeZoneId)
    val end = endDate!!.toInstantFromZone(userTimeZoneId)

    val identifier = name ?: ""

    val components = name!!.split("-")

    return when (components.size) {
        3 -> DayPeriod(
            components[2].toInt(),
            components[1].toInt(),
            components[0].toInt(),
            identifier,
            start,
            end
        )

        2 -> MonthPeriod(
            components[1].toInt(),
            components[0].toInt(),
            identifier,
            start,
            end
        )

        1 -> {
            components[0].split(":")
                .takeIf { it.size == 2 }
                ?.let { str ->
                    WeekPeriod(
                        str[1].toInt(),
                        str[0].toInt(),
                        identifier,
                        start,
                        end
                    )
                }
                ?: YearPeriod(components[0].toInt(), identifier, start, end)
        }
        else -> throw IllegalArgumentException("Unknown period type")
    }
}
