package com.tink.service.insight

import com.tink.model.insights.Insight
import com.tink.model.insights.PerformedInsightAction
import com.tink.service.handler.ResultHandler

interface InsightService {
    fun listInsights(resultHandler: ResultHandler<List<Insight>>)
    fun listArchived(resultHandler: ResultHandler<List<Insight>>)
    fun selectAction(performedAction: PerformedInsightAction, resultHandler: ResultHandler<Unit>)
}