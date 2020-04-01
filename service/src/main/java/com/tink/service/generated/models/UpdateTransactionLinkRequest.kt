/**
 * NOTE: This class is auto generated by the Swagger Gradle Codegen for the following API: Sample Pet Store App
 *
 * More info on this tool is available on https://github.com/Yelp/swagger-gradle-codegen
 */

package com.tink.service.generated.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @property amount The amount of the transaction part. Must be same sign as the transaction. If not specified the common disposable amount will be used.
 */
@JsonClass(generateAdapter = true)
data class UpdateTransactionLinkRequest(
    @Json(name = "amount") @field:Json(name = "amount") var amount: Double? = null
)
