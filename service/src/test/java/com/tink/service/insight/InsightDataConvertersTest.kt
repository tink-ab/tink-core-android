package com.tink.service.insight

import com.tink.model.insights.InsightData
import com.tink.model.misc.Amount
import com.tink.model.misc.ExactNumber
import com.tink.rest.models.ActionableInsight
import com.tink.rest.models.InsightData as InsightDataDto
import com.tink.rest.tools.GeneratedCodeConverters
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class InsightDataConvertersTest {

    @Test
    internal fun `account balance low data conversion`() {

        val insightDto = GeneratedCodeConverters.moshi
            .adapter(ActionableInsight::class.java)
            .fromJson(insightJson)

        val data = insightDto!!.data!!.toCoreModel() as InsightData.AccountBalanceLowData

        assertThat(data.balance).isEqualTo(Amount(ExactNumber(2.42), "EUR"))
        assertThat(data.accountId).isEqualTo("c6f26025fbb949a08348e2f73f0ae12c")
    }

    @Test
    internal fun `budget close negative data conversion`() {
        val insightDto = GeneratedCodeConverters.moshi
            .adapter(ActionableInsight::class.java)
            .fromJson(insightWithBudgetCloseNegativeData)

        val data = (insightDto!!.data as InsightDataDto.BudgetCloseNegative).data

        assertThat(data.budgetId).isEqualTo("cbbac116e43c4b21b7013091ec03d590")
        assertThat(data.budgetPeriod.budgetAmount.currencyCode).isEqualTo("EUR")
        assertThat(data.budgetPeriod.budgetAmount.amount).isEqualTo(120.0)
        assertThat(data.budgetPeriod.spentAmount.currencyCode).isEqualTo("EUR")
        assertThat(data.budgetPeriod.spentAmount.amount).isEqualTo(114.31)
        assertThat(data.budgetPeriod.start).isEqualTo(1567296000000)
        assertThat(data.budgetPeriod.end).isEqualTo(1569887999999)
        assertThat(data.currentTime).isEqualTo(1569593745000)
        assertThat(data.periodUnit).isEqualTo("MONTH")
    }
}