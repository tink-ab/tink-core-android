package com.tink.service.time

import com.tink.model.time.DayPeriod
import com.tink.model.time.MonthPeriod
import com.tink.model.time.WeekPeriod
import com.tink.model.time.YearPeriod
import com.tink.rest.models.Period
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class PeriodConvertersTest {

    @Test
    fun `period name yield correct period type`() {

        val periodDto = Period(
            startDate = 0,
            endDate = 0,
            name = "2020"
        )

        val yearPeriod = periodDto.toCoreModel("UTC") as YearPeriod
        assertThat(yearPeriod.year).isEqualTo(2020)

        val monthPeriod = periodDto.copy(name = "2019-12").toCoreModel("UTC") as MonthPeriod
        assertThat(monthPeriod.year).isEqualTo(2019)
        assertThat(monthPeriod.monthOfYear).isEqualTo(12)

        val dayPeriod = periodDto.copy(name = "2018-07-03").toCoreModel("UTC") as DayPeriod
        assertThat(dayPeriod.year).isEqualTo(2018)
        assertThat(dayPeriod.monthOfYear).isEqualTo(7)
        assertThat(dayPeriod.dayOfMonth).isEqualTo(3)

        val weekPeriod = periodDto.copy(name = "2014:45").toCoreModel("UTC") as WeekPeriod
        assertThat(weekPeriod.weekOfYear).isEqualTo(45)
        assertThat(weekPeriod.year).isEqualTo(2014)

        Assertions.assertThrows(IllegalArgumentException::class.java) {
            periodDto.copy(name = "2019-04-32").toCoreModel("UTC") as DayPeriod
        }
    }
}
