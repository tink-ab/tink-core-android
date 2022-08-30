/**
 * NOTE: This class is auto generated by the Swagger Gradle Codegen for the following API: Tink API
 *
 * More info on this tool is available on https://github.com/Yelp/swagger-gradle-codegen
 */

package com.tink.rest.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @property markets
 */
@JsonClass(generateAdapter = true)
data class ProviderMarketListResponse(
    @Json(name = "markets") @field:Json(name = "markets") var markets: List<String>? = null
)
