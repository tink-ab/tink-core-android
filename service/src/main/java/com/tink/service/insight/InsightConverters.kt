package com.tink.service.insight

import com.tink.model.insights.Insight
import com.tink.model.insights.InsightData
import com.tink.model.insights.InsightState
import com.tink.model.insights.InsightType
import com.tink.rest.models.ActionableInsight
import org.threeten.bp.Instant
import java.lang.IllegalArgumentException


internal fun ActionableInsight.toCoreModel(state: InsightState): Insight {
    return Insight(
        id = id ?: "",
        type = convertType(type),
        title = title ?: "",
        description = description ?: "",
        state = state,
        created = Instant.ofEpochMilli(createdTime ?: 0L),
        data = InsightData.NoData,
        actions = listOf()
    )
}

private fun convertType(typeString: String?): InsightType =
    try {
        InsightType.valueOf(typeString ?: "")
    } catch (e: IllegalArgumentException) {
        InsightType.UNKNOWN
    }
