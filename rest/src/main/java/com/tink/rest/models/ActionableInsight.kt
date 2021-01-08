/**
 * NOTE: This class is auto generated by the Swagger Gradle Codegen for the following API: Tink API
 *
 * More info on this tool is available on https://github.com/Yelp/swagger-gradle-codegen
 */

package com.tink.rest.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * An actionable insight represent some kind of actionable event or insight derived from user data. It could for instance be that a user has low balance on one of their bank accounts where the action could be to transfer money to that account. Another example could be to encourage a user to save more money by creating a budget for a specific category. Read more about it [here](https://docs.tink.com/resources/pfm/actionable-insights).
 * @property id The unique identifier of the insight.
 * @property userId The unique identifier of the user that the insight belongs to.
 * @property type Conveys the meaning of the Insight. The type will also indicate the structure of the data field
 * @property title The title of the insight which can be shown to the user.
 * @property description The description of the insight which can be shown to the user.
 * @property `data` The data that describes the basis for why this Insight was created. The content is determined by the Type. Read more about it [here](https://docs.tink.com/resources/pfm/actionable-insights).
 * @property createdTime The epoch timestamp in UTC when the insight was created.
 * @property insightActions A list of proposed actions that the user can take in response to the insight.
 */
@JsonClass(generateAdapter = true)
data class ActionableInsight(
    @Json(name = "userId") @field:Json(name = "userId") var userId: String,
    @Json(name = "id") @field:Json(name = "id") var id: String? = null,
    @Json(name = "type") @field:Json(name = "type") var type: String? = null,
    @Json(name = "title") @field:Json(name = "title") var title: String? = null,
    @Json(name = "description") @field:Json(name = "description") var description: String? = null,
    @Json(name = "data") @field:Json(name = "data") var data: InsightData? = null,
    @Json(name = "createdTime") @field:Json(name = "createdTime") var createdTime: Long? = null,
    @Json(name = "insightActions") @field:Json(name = "insightActions") var insightActions: List<InsightProposedAction>? = null
)
