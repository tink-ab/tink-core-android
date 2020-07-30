package com.tink.service.statistics

import com.tink.model.misc.Amount
import com.tink.model.misc.ExactNumber
import com.tink.model.statistics.Statistics
import com.tink.model.time.Period
import com.tink.rest.apis.StatisticsApi
import com.tink.rest.models.StatisticQuery
import com.tink.service.time.PeriodService
import com.tink.service.user.UserProfileService
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

private const val EXPENSES_IDENTIFIER = "expenses-by-category"
private const val INCOME_IDENTIFIER = "income-by-category"

interface StatisticsService {
    suspend fun query(): List<Statistics>
}

internal class StatisticsServiceImpl @Inject constructor(
    private val api: StatisticsApi,
    private val periodService: PeriodService,
    private val userProfileService: UserProfileService
) : StatisticsService {

    override suspend fun query(): List<Statistics> {

        val statisticDtos = api.query(
            StatisticQuery(
                resolution = StatisticQuery.ResolutionEnum.MONTHLY,
                types = listOf(EXPENSES_IDENTIFIER, INCOME_IDENTIFIER)
            )
        )

        val periodDescriptions = statisticDtos.map { it.period }.toSet()

        val periodsAsync = coroutineScope {
            periodDescriptions.map { async { periodService.getPeriod(it) } }
        }

        val userProfileAsync = coroutineScope {
            async { userProfileService.getProfile() }
        }

        val periods: Map<String, Period> =
            periodsAsync
                .awaitAll()
                .flatten()
                .mapNotNull { period ->
                    period.identifier.takeUnless { it.isBlank() }?.let { it to period }
                }
                .toMap()

        val userProfile = userProfileAsync.await()

        return statisticDtos.map {
            Statistics(
                identifier = it.description,
                type = it.type,
                period = periods.getValue(it.period),
                value = Amount(ExactNumber(it.value), userProfile.currency)
            )
        }
    }
}
