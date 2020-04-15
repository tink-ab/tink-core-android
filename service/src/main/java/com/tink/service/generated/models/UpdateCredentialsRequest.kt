/**
 * NOTE: This class is auto generated by the Swagger Gradle Codegen for the following API: Tink API
 *
 * More info on this tool is available on https://github.com/Yelp/swagger-gradle-codegen
 */

package com.tink.service.generated.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @property providerName
 * @property fields
 * @property callbackUri This URI will be used by the ASPSP to pass the authorization code. It corresponds to the redirect/callback URI in OAuth2/OpenId. This parameter is only applicable if you are a TPP.
 * @property appUri The end user will be redirected to this URI after the authorization code has been delivered.
 */
@JsonClass(generateAdapter = true)
data class UpdateCredentialsRequest(
    @Json(name = "providerName") @field:Json(name = "providerName") var providerName: String? = null,
    @Json(name = "fields") @field:Json(name = "fields") var fields: Map<String, String>? = null,
    @Json(name = "callbackUri") @field:Json(name = "callbackUri") var callbackUri: String? = null,
    @Json(name = "appUri") @field:Json(name = "appUri") var appUri: String? = null
)
