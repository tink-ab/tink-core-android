/**
 * NOTE: This class is auto generated by the Swagger Gradle Codegen for the following API: Sample Pet Store App
 *
 * More info on this tool is available on https://github.com/Yelp/swagger-gradle-codegen
 */

package com.tink.service.generated.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @property key
 * @property value
 */
@JsonClass(generateAdapter = true)
data class StringDoublePair(
    @Json(name = "key") @field:Json(name = "key") var key: String? = null,
    @Json(name = "value") @field:Json(name = "value") var value: Double? = null
)
