package com.tink.service.statistics

import com.tink.model.misc.Amount
import com.tink.model.misc.ExactNumber
import com.tink.model.statistic.StatisticDataNode
import com.tink.model.statistic.StatisticTree
import com.tink.model.statistic.StatisticsGroupNode
import com.tink.model.time.Period
import com.tink.rest.apis.StatisticsApi
import com.tink.rest.models.StatisticQuery
import com.tink.rest.models.Statistics
import com.tink.service.observer.ChangeObserver
import com.tink.service.time.PeriodService
import com.tink.service.user.UserProfileService
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

private const val EXPENSES_IDENTIFIER = "expenses-by-category"
private const val INCOME_IDENTIFIER = "income-by-category"

interface StatisticsService {
    fun subscribe(listener: ChangeObserver<StatisticTree>)
    fun unsubscribe(listener: ChangeObserver<StatisticTree>)
    fun refreshStatistics()
    suspend fun query(): StatisticTree
}

internal class StatisticsServiceImpl @Inject constructor(
    private val api: StatisticsApi,
    private val periodService: PeriodService,
    private val userProfileService: UserProfileService
) : StatisticsService {
    override fun subscribe(listener: ChangeObserver<StatisticTree>) {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun unsubscribe(listener: ChangeObserver<StatisticTree>) {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun refreshStatistics() {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun query(): StatisticTree {

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

        fun List<Statistics>.byDescription() =
            map {
                StatisticDataNode(
                    identifier = it.description,
                    period = periods.getValue(it.period),
                    value = Amount(ExactNumber(it.value), userProfile.currency)
                )
            }

        fun List<Statistics>.byPeriod() =
            groupBy { it.period }
                .map { StatisticsGroupNode(it.key, it.value.byDescription()) }

        fun List<Statistics>.byType() =
            groupBy { it.type }
                .map { StatisticsGroupNode(it.key, it.value.byPeriod()) }

        val statistics = statisticDtos.byType()

        return StatisticTree(
            expensesByCategoryCode = statistics.first { it.identifier == EXPENSES_IDENTIFIER },
            incomeByCategoryCode = statistics.first { it.identifier == INCOME_IDENTIFIER }
        )
    }
}
