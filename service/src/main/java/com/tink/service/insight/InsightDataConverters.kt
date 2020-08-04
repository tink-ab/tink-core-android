package com.tink.service.insight

import com.tink.model.insights.InsightData
import com.tink.model.misc.Amount
import com.tink.model.misc.ExactNumber
import com.tink.rest.models.insightdata.AmountWithCurrencyCode
import com.tink.rest.models.InsightData as InsightDataDto

fun InsightDataDto.toCoreModel() : InsightData {
    return when (this) {
        is com.tink.rest.models.InsightData.AccountBalanceLow -> InsightData.AccountBalanceLowData(
            data.accountId,
            data.balance.toAmount()
        )
        else -> InsightData.NoData //TODO
    }
}

fun AmountWithCurrencyCode.toAmount() = Amount(ExactNumber(amount), currencyCode)
