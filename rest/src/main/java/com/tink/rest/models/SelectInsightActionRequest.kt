/**
 * NOTE: This class is auto generated by the Swagger Gradle Codegen for the following API: Tink API
 *
 * More info on this tool is available on https://github.com/Yelp/swagger-gradle-codegen
 */

package com.tink.rest.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @property insightId The unique identifier of an existing insight.
 * @property insightAction The insight action that the user invoked for the insight.
 */
@JsonClass(generateAdapter = true)
data class SelectInsightActionRequest(
    @Json(name = "insightId") @field:Json(name = "insightId") var insightId: String,
    @Json(name = "insightAction") @field:Json(name = "insightAction") var insightAction: String
)
