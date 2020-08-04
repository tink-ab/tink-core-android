package com.tink.rest.tools

import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import com.tink.rest.models.InsightData

object TinkPolymorphicInsightsDataAdapterFactory {

    fun create(): PolymorphicJsonAdapterFactory<InsightData> =
        PolymorphicJsonAdapterFactory.of(InsightData::class.java, "type")
            .withSubtype(
                InsightData.AccountBalanceLow::class.java,
                InsightData.TypeEnum.ACCOUNT_BALANCE_LOW.value
            )
            .withSubtype(
                InsightData.BudgetCloseNegative::class.java,
                InsightData.TypeEnum.BUDGET_CLOSE_NEGATIVE.value
            )

}