package com.tink.service.statistics

import com.tink.model.misc.Amount
import com.tink.model.misc.ExactNumber
import com.tink.model.statistics.Statistics
import com.tink.model.time.Period
import com.tink.model.user.UserProfile
import com.tink.rest.apis.StatisticsApi
import com.tink.rest.models.StatisticQuery
import com.tink.service.time.PeriodService
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject
import com.tink.rest.models.Statistics as StatisticsDto

private const val EXPENSES_IDENTIFIER = "expenses-by-category"
private const val INCOME_IDENTIFIER = "income-by-category"

interface StatisticsService {
    suspend fun query(queryDescriptor: StatisticsQueryDescriptor): List<Statistics>
}

data class StatisticsQueryDescriptor(val periodMode: UserProfile.PeriodMode, val currencyCode: String)

internal class StatisticsServiceImpl @Inject constructor(
    private val api: StatisticsApi,
    private val periodService: PeriodService
) : StatisticsService {

    override suspend fun query(queryDescriptor: StatisticsQueryDescriptor): List<Statistics> {
        val resolution =
            when (queryDescriptor.periodMode) {
                is UserProfile.PeriodMode.Monthly -> StatisticQuery.ResolutionEnum.MONTHLY
                is UserProfile.PeriodMode.MonthlyAdjusted -> StatisticQuery.ResolutionEnum.MONTHLY_ADJUSTED
            }
        val statisticDtos = api.query(
            StatisticQuery(
                resolution = resolution,
                types = listOf(
                    Statistics.Type.EXPENSES_BY_CATEGORY.value,
                    Statistics.Type.INCOME_BY_CATEGORY.value
                )
            )
        )

        val periodDescriptions = statisticDtos.map {
            getYearFromPeriodString(it.period)
        }.toSet()

        val periodsAsync = coroutineScope {
            periodDescriptions.map { async { periodService.getPeriod(it) } }
        }

        val periods: Map<String, Period> =
            periodsAsync
                .awaitAll()
                .flatten()
                .mapNotNull { period ->
                    period.identifier.takeUnless { it.isBlank() }?.let { it to period }
                }
                .toMap()

        return statisticDtos.map {
            Statistics(
                identifier = it.description,
                type = it.getType(),
                period = periods.getValue(it.period),
                value = Amount(ExactNumber(it.value), queryDescriptor.currencyCode)
            )
        }
    }

    private fun getYearFromPeriodString(period: String): String =
        if (period.length >= 4) {
            period.substring(0, 4)
        } else {
            period
        }

    private fun StatisticsDto.getType(): Statistics.Type =
        when (type) {
            EXPENSES_IDENTIFIER -> Statistics.Type.EXPENSES_BY_CATEGORY
            INCOME_IDENTIFIER -> Statistics.Type.INCOME_BY_CATEGORY
            else -> Statistics.Type.UNKNOWN
        }
}
