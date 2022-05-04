package com.tink.service.time

import com.tink.model.time.Period
import com.tink.rest.apis.CalendarApi
import javax.inject.Inject

interface PeriodService {
    suspend fun getPeriod(
        name: String,
        userTimeZoneId: String
    ): List<Period>
}

internal class PeriodServiceImpl @Inject constructor(
    private val api: CalendarApi
) : PeriodService {
    override suspend fun getPeriod(
        name: String,
        userTimeZoneId: String
    ): List<Period> =
        api.listPeriods(name).map { it.toCoreModel(userTimeZoneId) }
}
