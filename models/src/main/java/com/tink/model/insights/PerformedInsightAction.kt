package com.tink.model.insights

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PerformedInsightAction(
    val insightId: String,
    val userId: String,
    val actionType: InsightAction.Type
) : Parcelable
