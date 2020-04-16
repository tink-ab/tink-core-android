/**
 * NOTE: This class is auto generated by the Swagger Gradle Codegen for the following API: Tink API
 *
 * More info on this tool is available on https://github.com/Yelp/swagger-gradle-codegen
 */

package com.tink.service.generated.apis

import com.tink.service.generated.models.BusinessDaysResponse
import com.tink.service.generated.models.Period
import retrofit2.http.GET
import retrofit2.http.Headers

@JvmSuppressWildcards
interface CalendarApi {
    /**
     * Get business days
     * Get the business days available for this user.
     * @param startYear Start year for queried business days (required)
     * @param startMonth Start month for queried business days (required)
     * @param months Number of months queried for. Defaults to 1. (optional)
     */
    @Headers(
        "X-Operation-ID: businessDays"
    )
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
    @Headers(
        "X-Operation-ID: listPeriods"
    )
    @GET("/api/v1/calendar/periods/{period}")
    suspend fun listPeriods(
        @retrofit2.http.Path("period") period: String
    ): List<Period>
}
