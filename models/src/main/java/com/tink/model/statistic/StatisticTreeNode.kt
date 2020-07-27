package com.tink.model.statistic

import android.os.Parcelable
import com.tink.model.misc.Amount
import com.tink.model.time.Period
import kotlinx.android.parcel.Parcelize

sealed class StatisticsTreeNode : Parcelable {
    abstract val identifier: String
}

@Parcelize
data class StatisticDataNode(
    override val identifier: String,
    val period: Period,
    val value: Amount
) : StatisticsTreeNode()

@Parcelize
data class StatisticsGroupNode(
    override val identifier: String,
    val children: List<StatisticsTreeNode>
) : StatisticsTreeNode()
