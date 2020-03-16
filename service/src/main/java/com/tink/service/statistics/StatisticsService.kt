package com.tink.service.statistics

import com.tink.model.statistic.StatisticTree
import com.tink.service.observer.ChangeObserver

interface StatisticsService {
    fun subscribe(listener: ChangeObserver<StatisticTree>)
    fun unsubscribe(listener: ChangeObserver<StatisticTree>)
    fun refreshStatistics()
}