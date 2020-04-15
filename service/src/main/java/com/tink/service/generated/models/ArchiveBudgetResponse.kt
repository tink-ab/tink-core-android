/**
 * NOTE: This class is auto generated by the Swagger Gradle Codegen for the following API: Tink API
 *
 * More info on this tool is available on https://github.com/Yelp/swagger-gradle-codegen
 */

package com.tink.service.generated.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @property budgetSpecification The archived budget.
 */
@JsonClass(generateAdapter = true)
data class ArchiveBudgetResponse(
    @Json(name = "budgetSpecification") @field:Json(name = "budgetSpecification") var budgetSpecification: Budget? = null
)
