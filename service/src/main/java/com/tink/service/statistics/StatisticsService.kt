package com.tink.service.statistics

import com.tink.model.statistic.StatisticTree
import com.tink.service.observer.ChangeObserver
import javax.inject.Inject

interface StatisticsService {
    fun subscribe(listener: ChangeObserver<StatisticTree>)
    fun unsubscribe(listener: ChangeObserver<StatisticTree>)
    fun refreshStatistics()
}

class StatisticsServiceImpl @Inject constructor() : StatisticsService {
    override fun subscribe(listener: ChangeObserver<StatisticTree>) {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun unsubscribe(listener: ChangeObserver<StatisticTree>) {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun refreshStatistics() {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }
}