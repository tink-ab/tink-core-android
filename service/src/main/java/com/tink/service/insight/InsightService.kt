package com.tink.service.insight

import com.tink.model.insights.Insight
import com.tink.model.insights.PerformedInsightAction
import com.tink.service.handler.ResultHandler
import javax.inject.Inject

interface InsightService {
    fun listInsights(resultHandler: ResultHandler<List<Insight>>)
    fun listArchived(resultHandler: ResultHandler<List<Insight>>)
    fun selectAction(performedAction: PerformedInsightAction, resultHandler: ResultHandler<Unit>)
}

class InsightServiceImpl @Inject constructor() : InsightService {
    override fun listInsights(resultHandler: ResultHandler<List<Insight>>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun listArchived(resultHandler: ResultHandler<List<Insight>>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun selectAction(
        performedAction: PerformedInsightAction,
        resultHandler: ResultHandler<Unit>
    ) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}