/**
 * NOTE: This class is auto generated by the Swagger Gradle Codegen for the following API: Sample Pet Store App
 *
 * More info on this tool is available on https://github.com/Yelp/swagger-gradle-codegen
 */

package com.tink.service.generated.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.threeten.bp.ZonedDateTime

/**
 * The credentials model represents a user's connected providers from where financial data is accessed.
 * @property id The unique identifier of the credentials.
 * @property providerName The provider (financial institution) that the credentials is connected to.
 * @property type Indicates how Tink authenticates the user to the financial institution.
 * @property status The status indicates the state of the credentials. For some states there are actions which need to be performed on the credentials.
 * @property statusUpdated A timestamp of when the credentials&#39; status was last modified.
 * @property statusPayload A user-friendly message connected to the status. Could be an error message or text describing what is currently going on in the refresh process.
 * @property updated A timestamp of when the credentials was the last time in status &#x60;UPDATED&#x60;.
 * @property fields This is a key-value map of &#x60;Field&#x60; name and value found on the &#x60;Provider&#x60; to which the credentials belongs to. This parameter is required when creating credentials.
 * @property supplementalInformation A key-value structure to handle if status of credentials are &#x60;AWAITING_SUPPLEMENTAL_INFORMATION&#x60; or &#x60;AWAITING_THIRD_PARTY_APP_AUTHENTICATION&#x60;.
 * @property sessionExpiryDate For credentials with access type of &#x60;OPEN_BANKING&#x60;, indicates when the session for the currently stored credentials will expire. The session can be renewed before or after this date by triggering manual authentication of credentials. After this date automatic refreshes will not be possible without new authentication from the user.
 * @property userId The ID of the user that the credentials belongs to.
 */
@JsonClass(generateAdapter = true)
data class Credentials(
    @Json(name = "providerName") @field:Json(name = "providerName") var providerName: String,
    @Json(name = "fields") @field:Json(name = "fields") var fields: Map<String, String>,
    @Json(name = "id") @field:Json(name = "id") var id: String? = null,
    @Json(name = "type") @field:Json(name = "type") var type: Credentials.TypeEnum? = null,
    @Json(name = "status") @field:Json(name = "status") var status: Credentials.StatusEnum? = null,
    @Json(name = "statusUpdated") @field:Json(name = "statusUpdated") var statusUpdated: ZonedDateTime? = null,
    @Json(name = "statusPayload") @field:Json(name = "statusPayload") var statusPayload: String? = null,
    @Json(name = "updated") @field:Json(name = "updated") var updated: ZonedDateTime? = null,
    @Json(name = "supplementalInformation") @field:Json(name = "supplementalInformation") var supplementalInformation: String? = null,
    @Json(name = "sessionExpiryDate") @field:Json(name = "sessionExpiryDate") var sessionExpiryDate: ZonedDateTime? = null,
    @Json(name = "userId") @field:Json(name = "userId") var userId: String? = null
) {
    /**
     * Indicates how Tink authenticates the user to the financial institution.
     * Values: PASSWORD, MOBILE_BANKID, KEYFOB, THIRD_PARTY_APP
     */
    @JsonClass(generateAdapter = false)
    enum class TypeEnum(val value: String) {
        @Json(name = "PASSWORD") PASSWORD("PASSWORD"),
        @Json(name = "MOBILE_BANKID") MOBILE_BANKID("MOBILE_BANKID"),
        @Json(name = "KEYFOB") KEYFOB("KEYFOB"),
        @Json(name = "THIRD_PARTY_APP") THIRD_PARTY_APP("THIRD_PARTY_APP")
    }
    /**
     * The status indicates the state of the credentials. For some states there are actions which need to be performed on the credentials.
     * Values: CREATED, AUTHENTICATING, AWAITING_MOBILE_BANKID_AUTHENTICATION, AWAITING_SUPPLEMENTAL_INFORMATION, UPDATING, UPDATED, AUTHENTICATION_ERROR, TEMPORARY_ERROR, PERMANENT_ERROR, AWAITING_THIRD_PARTY_APP_AUTHENTICATION, DELETED, SESSION_EXPIRED
     */
    @JsonClass(generateAdapter = false)
    enum class StatusEnum(val value: String) {
        @Json(name = "CREATED") CREATED("CREATED"),
        @Json(name = "AUTHENTICATING") AUTHENTICATING("AUTHENTICATING"),
        @Json(name = "AWAITING_MOBILE_BANKID_AUTHENTICATION") AWAITING_MOBILE_BANKID_AUTHENTICATION("AWAITING_MOBILE_BANKID_AUTHENTICATION"),
        @Json(name = "AWAITING_SUPPLEMENTAL_INFORMATION") AWAITING_SUPPLEMENTAL_INFORMATION("AWAITING_SUPPLEMENTAL_INFORMATION"),
        @Json(name = "UPDATING") UPDATING("UPDATING"),
        @Json(name = "UPDATED") UPDATED("UPDATED"),
        @Json(name = "AUTHENTICATION_ERROR") AUTHENTICATION_ERROR("AUTHENTICATION_ERROR"),
        @Json(name = "TEMPORARY_ERROR") TEMPORARY_ERROR("TEMPORARY_ERROR"),
        @Json(name = "PERMANENT_ERROR") PERMANENT_ERROR("PERMANENT_ERROR"),
        @Json(name = "AWAITING_THIRD_PARTY_APP_AUTHENTICATION") AWAITING_THIRD_PARTY_APP_AUTHENTICATION("AWAITING_THIRD_PARTY_APP_AUTHENTICATION"),
        @Json(name = "DELETED") DELETED("DELETED"),
        @Json(name = "SESSION_EXPIRED") SESSION_EXPIRED("SESSION_EXPIRED")
    }
}
