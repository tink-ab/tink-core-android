/**
 * NOTE: This class is auto generated by the Swagger Gradle Codegen for the following API: Tink API
 *
 * More info on this tool is available on https://github.com/Yelp/swagger-gradle-codegen
 */

package com.tink.rest.apis

import com.tink.rest.models.BusinessDaysResponse
import com.tink.rest.models.Period
import retrofit2.http.GET

@JvmSuppressWildcards
interface CalendarApi {
    /**
     * Get business days
     * Get the business days available for this user.
     * @param startYear Start year for queried business days (required)
     * @param startMonth Start month for queried business days (required)
     * @param months Number of months queried for. Defaults to 1. (optional)
     */
    @GET("/api/v1/calendar/businessdays/{startYear}-{startMonth}")
    suspend fun businessDays(
        @retrofit2.http.Path("startYear") startYear: Int,
        @retrofit2.http.Path("startMonth") startMonth: Int,
        @retrofit2.http.Query("months") months: Int?
    ): BusinessDaysResponse
    /**
     * Get period details
     * Get details for the supplied period. Will always return one of the monthly resolutions.
     * @param period Period to get details for (required)
     */
    @GET("/api/v1/calendar/periods/{period}")
    suspend fun listPeriods(
        @retrofit2.http.Path("period") period: String
    ): List<Period>
}
