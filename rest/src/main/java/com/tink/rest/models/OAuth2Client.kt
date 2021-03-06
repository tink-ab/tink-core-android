/**
 * NOTE: This class is auto generated by the Swagger Gradle Codegen for the following API: Tink API
 *
 * More info on this tool is available on https://github.com/Yelp/swagger-gradle-codegen
 */

package com.tink.rest.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @property name
 * @property id
 * @property redirectUris
 * @property scope
 * @property secret
 * @property url
 * @property iconUrl
 * @property verified
 * @property hasConfiguredProviders
 */
@JsonClass(generateAdapter = true)
data class OAuth2Client(
    @Json(name = "name") @field:Json(name = "name") var name: String? = null,
    @Json(name = "id") @field:Json(name = "id") var id: String? = null,
    @Json(name = "redirectUris") @field:Json(name = "redirectUris") var redirectUris: List<String>? = null,
    @Json(name = "scope") @field:Json(name = "scope") var scope: String? = null,
    @Json(name = "secret") @field:Json(name = "secret") var secret: String? = null,
    @Json(name = "url") @field:Json(name = "url") var url: String? = null,
    @Json(name = "iconUrl") @field:Json(name = "iconUrl") var iconUrl: String? = null,
    @Json(name = "verified") @field:Json(name = "verified") var verified: Boolean? = null,
    @Json(name = "hasConfiguredProviders") @field:Json(name = "hasConfiguredProviders") var hasConfiguredProviders: Boolean? = null
)
