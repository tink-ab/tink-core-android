/**
 * NOTE: This class is auto generated by the Swagger Gradle Codegen for the following API: Tink API
 *
 * More info on this tool is available on https://github.com/Yelp/swagger-gradle-codegen
 */

package com.tink.service.generated.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @property locale
 * @property message
 */
@JsonClass(generateAdapter = true)
data class ClientMessage(
    @Json(name = "locale") @field:Json(name = "locale") var locale: String? = null,
    @Json(name = "message") @field:Json(name = "message") var message: String? = null
)
