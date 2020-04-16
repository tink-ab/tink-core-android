/**
 * NOTE: This class is auto generated by the Swagger Gradle Codegen for the following API: Tink API
 *
 * More info on this tool is available on https://github.com/Yelp/swagger-gradle-codegen
 */

package com.tink.service.generated.apis

import com.tink.service.generated.models.AuthenticationContext
import com.tink.service.generated.models.StatisticQuery
import com.tink.service.generated.models.Statistics
import retrofit2.http.Headers
import retrofit2.http.POST

@JvmSuppressWildcards
interface StatisticsApi {
    /**
     * Query statistics
     * By querying the statistics endpoint, an API customer can select the specific types of data to access. The query should be posted in the request body and you can specify any of the properties available to filter the result set. Defining multiple properties will yield an `AND` operation, and specifying multiple values of a property will yield an `OR` operation. Note: Monthly statistics will be calculated only with the resolution that the user has in the user settings (MONTHLY, MONTHLY_ADJUSTED), and not for both. Note: `YEARLY` resolution is aggregated based on the resolution that the user has in the user settings. With `MONTHLY` it will be the calendar year and with `MONTHLY_ADJUSTED` it will be adjusted based on the users' `periodbreakday`  ### Statistics types  Type | Value of description field | Description | Available resolutions ---- | ----------------- | ----------------- | ----------------- `balances-by-account` | Identifier of an account | Balances over time by each account | `MONTHLY` `MONTHLY_ADJUSTED` `balances-by-account-type-group` | The type group name | Balances over time by each account group | `MONTHLY` `MONTHLY_ADJUSTED` `expenses-by-category` | Identifier of a category | Sum of expenses per period in each category | `MONTHLY` `MONTHLY_ADJUSTED` `YEARLY` `DAILY` `WEEKLY` `expenses-by-primary-category` | Identifier of a primary category | Combined sum of all expenses of sub-categories per period in each primary category | `MONTHLY` `MONTHLY_ADJUSTED` `YEARLY` `DAILY` `WEEKLY` `expenses-by-category/by-count` | Identifier of a category | Count of expenses per period in each category | `MONTHLY` `MONTHLY_ADJUSTED` `DAILY` `WEEKLY` `expenses-by-primary-category/by-count` | Identifier of a primary category | Combined count of all expenses of sub-catetories per period in each primary category | `MONTHLY` `MONTHLY_ADJUSTED` `DAILY` `WEEKLY` `income-by-category` | Identifier of a category | Sum of Incomes per period in each category | `MONTHLY` `MONTHLY_ADJUSTED` `income-and-expenses` |  | Sum of transactions per period for category type | `MONTHLY` `MONTHLY_ADJUSTED` `YEARLY` `left-to-spend` | The date | Takes income minus expenses over time | `MONTHLY` `MONTHLY_ADJUSTED` `left-to-spend-average` | The date | Average income minus expenses over 6 months | `MONTHLY` `MONTHLY_ADJUSTED`
     * @param body2 The query object (required)
     * @param body (optional)
     */
    @Headers(
        "X-Operation-ID: query",
        "Content-Type: application/json"
    )
    @POST("/api/v1/statistics/query")
    suspend fun query(
        @retrofit2.http.Body body2: StatisticQuery,
        @retrofit2.http.Body body: AuthenticationContext
    ): List<Statistics>
}
