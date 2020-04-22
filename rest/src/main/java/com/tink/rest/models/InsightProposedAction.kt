/**
 * NOTE: This class is auto generated by the Swagger Gradle Codegen for the following API: Tink API
 *
 * More info on this tool is available on https://github.com/Yelp/swagger-gradle-codegen
 */

package com.tink.rest.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @property label The action label
 * @property `data` The data that describes the action.The content is determined by the Type. Read more about it [here](https://docs.tink.com/resources/pfm/actionable-insights).
 */
@JsonClass(generateAdapter = true)
data class InsightProposedAction(
    @Json(name = "label") @field:Json(name = "label") var label: String? = null,
    @Json(name = "data") @field:Json(name = "data") var `data`: InsightActionData? = null
)
