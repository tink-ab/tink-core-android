package com.tink.service.insight

import com.tink.model.insights.Insight
import com.tink.model.insights.InsightData
import com.tink.model.insights.InsightState
import com.tink.model.insights.InsightType
import com.tink.rest.models.ActionableInsight
import com.tink.rest.models.ArchivedInsight
import com.tink.service.misc.toInstant
import java.lang.IllegalArgumentException

internal fun ActionableInsight.toCoreModel(): Insight {
    return Insight(
        id = id ?: "",
        type = convertType(type),
        title = title ?: "",
        description = description ?: "",
        state = InsightState.Active,
        created = createdTime.toInstant(),
        data = data?.toCoreModel() ?: InsightData.NoData,
        actions = insightActions?.map { it.toCoreModel() } ?: listOf()
    )
}

internal fun ArchivedInsight.toCoreModel() =
    Insight(
        id = id ?: "",
        type = convertType(type),
        title = title ?: "",
        description = description ?: "",
        state = InsightState.Archived(dateArchived.toInstant()),
        created = dateInsightCreated.toInstant(),
        data = data?.toCoreModel() ?: InsightData.NoData,
        actions = listOf()
    )

private fun convertType(typeString: String?): InsightType =
    try {
        InsightType.valueOf(typeString ?: "")
    } catch (e: IllegalArgumentException) {
        InsightType.UNKNOWN
    }
