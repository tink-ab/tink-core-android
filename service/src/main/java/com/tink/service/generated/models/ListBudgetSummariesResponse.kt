/**
 * NOTE: This class is auto generated by the Swagger Gradle Codegen for the following API: Tink API
 *
 * More info on this tool is available on https://github.com/Yelp/swagger-gradle-codegen
 */

package com.tink.service.generated.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @property budgetSummaries List of budget summaries.
 */
@JsonClass(generateAdapter = true)
data class ListBudgetSummariesResponse(
    @Json(name = "budgetSummaries") @field:Json(name = "budgetSummaries") var budgetSummaries: List<BudgetSummary>? = null
)
