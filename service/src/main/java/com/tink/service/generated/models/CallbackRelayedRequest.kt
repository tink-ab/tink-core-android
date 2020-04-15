/**
 * NOTE: This class is auto generated by the Swagger Gradle Codegen for the following API: Tink API
 *
 * More info on this tool is available on https://github.com/Yelp/swagger-gradle-codegen
 */

package com.tink.service.generated.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @property state The state from the received callback from the ASPSP. Used by Tink to connect the incoming callback to the correct session.
 * @property parameters The post parameters from the received callback from the ASPSP. Contains the parameters necessary for the integration to continue the communication with the ASPSP.
 */
@JsonClass(generateAdapter = true)
data class CallbackRelayedRequest(
    @Json(name = "state") @field:Json(name = "state") var state: String,
    @Json(name = "parameters") @field:Json(name = "parameters") var parameters: Map<String, String>
)
