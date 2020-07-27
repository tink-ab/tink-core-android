package com.tink.service.time

import com.tink.model.time.DayPeriod
import com.tink.model.time.MonthPeriod
import com.tink.model.time.Period
import com.tink.model.time.YearPeriod
import com.tink.service.misc.toInstant
import java.lang.IllegalArgumentException
import com.tink.rest.models.Period as PeriodDto

internal fun PeriodDto.toCoreModel(): Period {

    val start = startDate!!.toInstant()
    val end = endDate!!.toInstant()

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
                ?.let {
                    TODO("Week period")
                }
                ?: YearPeriod(components[0].toInt(), identifier, start, end)
        }
        else -> throw IllegalArgumentException("Unknown period type")
    }
}