package com.tink.service.insight

import com.tink.model.insights.Insight
import com.tink.model.insights.InsightState
import com.tink.model.insights.PerformedInsightAction
import com.tink.rest.apis.ActionableInsightApi
import com.tink.service.handler.ResultHandler
import org.threeten.bp.Instant
import javax.inject.Inject

interface InsightService {
    suspend fun listInsights(): List<Insight>
    suspend fun listArchived(): List<Insight>
    suspend fun selectAction(performedAction: PerformedInsightAction)
}

class InsightServiceImpl @Inject constructor(
    val api: ActionableInsightApi
) : InsightService {
    override suspend fun listInsights(): List<Insight> =
        api.list().map { it.toCoreModel() }

    override suspend fun listArchived(): List<Insight> =
        api.listArchivedInsights().map { it.toCoreModel() }

    override suspend fun selectAction(performedAction: PerformedInsightAction) {
        TODO("not implemented")
    }
}
