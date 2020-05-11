/**
 * NOTE: This class is auto generated by the Swagger Gradle Codegen for the following API: Tink API
 *
 * More info on this tool is available on https://github.com/Yelp/swagger-gradle-codegen
 */

package com.tink.rest.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * A Transfer on Tink represents the tentative action of requesting a payment initiation. By consequence, its success does not represent that money has been successfully transferred from one account to another because the payment initiation relays the responsibility of properly executing the monetary reallocation to the financial institution.  The source account must belong to the authenticated user. Source and destination accounts are sent in a special URI format. See below examples.
 * @property amount The amount that will be transferred. Should be positive.
 * @property credentialsId The id of the Credentials used to make the transfer. For PIS with AIS will be the credentials of which the source account belongs to. For PIS without AIS it is not linked to source account
 * @property currency The currency of the amount to be transferred. Should match the SourceUri&#39;s currency.
 * @property destinationMessage The message to the recipient. Optional for bank transfers but required for payments. If the payment recipient requires a structured (specially formatted) message, it should be set in this field.
 * @property id
 * @property sourceMessage The transaction description on the source account for the transfer.
 * @property dueDate The date when the payment or bank transfer should be executed. If no dueDate is given, it will be executed immediately.
 * @property messageType Transfer&#39;s message type, only required for BE and SEPA-EUR schemes. STRUCTURED is for PAYMENT type transfers and FREE_TEXT is for BANK_TRANSFER type transfers.
 * @property destinationUri The destination account or recipient of the transfer, in the form of a uri.
 * @property sourceUri The source account of the transfer, in the form of a uri.
 */
@JsonClass(generateAdapter = true)
data class Transfer(
    @Json(name = "amount") @field:Json(name = "amount") var amount: Double, // TODO
    @Json(name = "currency") @field:Json(name = "currency") var currency: String,
    @Json(name = "destinationMessage") @field:Json(name = "destinationMessage") var destinationMessage: String,
    @Json(name = "destinationUri") @field:Json(name = "destinationUri") var destinationUri: String,
    @Json(name = "sourceUri") @field:Json(name = "sourceUri") var sourceUri: String,
    @Json(name = "credentialsId") @field:Json(name = "credentialsId") var credentialsId: String? = null,
    @Json(name = "id") @field:Json(name = "id") var id: String? = null,
    @Json(name = "sourceMessage") @field:Json(name = "sourceMessage") var sourceMessage: String? = null,
    @Json(name = "dueDate") @field:Json(name = "dueDate") var dueDate: Long? = null,
    @Json(name = "messageType") @field:Json(name = "messageType") var messageType: MessageTypeEnum? = null
) {
    /**
     * Transfer's message type, only required for BE and SEPA-EUR schemes. STRUCTURED is for PAYMENT type transfers and FREE_TEXT is for BANK_TRANSFER type transfers.
     * Values: STRUCTURED, FREE_TEXT
     */
    @JsonClass(generateAdapter = false)
    enum class MessageTypeEnum(val value: String) {
        @Json(name = "STRUCTURED") STRUCTURED("STRUCTURED"),
        @Json(name = "FREE_TEXT") FREE_TEXT("FREE_TEXT")
    }
    /**
     * The destination account or recipient of the transfer, in the form of a uri.
     * Values: SEPAMINUSEUR, SEMINUSBG, SEMINUSPG
     */
    @JsonClass(generateAdapter = false)
    enum class DestinationUriEnum(val value: String) {
        @Json(name = "sepa-eur") SEPAMINUSEUR("sepa-eur"),
        @Json(name = "se-bg") SEMINUSBG("se-bg"),
        @Json(name = "se-pg") SEMINUSPG("se-pg")
    }
}
