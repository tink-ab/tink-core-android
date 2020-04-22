/**
 * NOTE: This class is auto generated by the Swagger Gradle Codegen for the following API: Tink API
 *
 * More info on this tool is available on https://github.com/Yelp/swagger-gradle-codegen
 */

package com.tink.rest.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @property tokenType The type of authorization token returned.
 * @property expiresIn The amount of time in seconds for the expiration of the token.
 * @property accessToken The access token that can be used to access an API resource. It should be treated as opaque (no defined format).
 * @property refreshToken The refresh token that can be used to get a new access token. It should be treated as opaque (no defined format).
 * @property scope The scope valid for the returned token.
 * @property idHint Human readable information about the identity of user
 */
@JsonClass(generateAdapter = true)
data class OAuth2AuthenticationTokenResponse(
    @Json(name = "token_type") @field:Json(name = "token_type") var tokenType: String,
    @Json(name = "expires_in") @field:Json(name = "expires_in") var expiresIn: Int,
    @Json(name = "access_token") @field:Json(name = "access_token") var accessToken: String,
    @Json(name = "refresh_token") @field:Json(name = "refresh_token") var refreshToken: String,
    @Json(name = "scope") @field:Json(name = "scope") var scope: String,
    @Json(name = "id_hint") @field:Json(name = "id_hint") var idHint: String? = null
)
