/**
 * NOTE: This class is auto generated by the Swagger Gradle Codegen for the following API: Tink API
 *
 * More info on this tool is available on https://github.com/Yelp/swagger-gradle-codegen
 */

package com.tink.service.generated.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * The provider list response object.
 * @property providers
 */
@JsonClass(generateAdapter = true)
data class ProviderListResponse(
    @Json(name = "providers") @field:Json(name = "providers") var providers: List<Provider>? = null
)
