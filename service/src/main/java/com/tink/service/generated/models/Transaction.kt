/**
 * NOTE: This class is auto generated by the Swagger Gradle Codegen for the following API: Sample Pet Store App
 *
 * More info on this tool is available on https://github.com/Yelp/swagger-gradle-codegen
 */

package com.tink.service.generated.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.math.BigDecimal
import org.threeten.bp.ZonedDateTime

/**
 * An account usually contains multiple transactions (except for certain types of accounts where Tink can't access the underlying transactions, for example, certain `INVESTMENT` accounts). The transaction model represents any operation on an account, and could represent both the actual credit-card purchase on a `CREDIT_CARD` account, but also represent the transaction when you paid your credit-card bill. Most commonly, the transactions in an account should represent what the end-user typically regards as a transaction with its amount, description and date, etc.
 * @property accountId The internal identifier of the account that the transaction belongs to.
 * @property amount The amount of the transaction. This can be modified by the user.
 * @property categoryId The category of the transaction. This can be modified by the user.
 * @property categoryType The category type of the transaction.
 * @property credentialsId (DEPRECATED) The internal identifier of the credentials that the transaction belongs to. This is deprecated and will soon be removed. This information can instead be accessed through the account. Account can be located with the transactions accountId.
 * @property date The date the transaction was executed. This can be modified by the user.
 * @property description The description of the transaction. This can be modified by the user.
 * @property id The internal identifier of the transaction.
 * @property lastModified The date the transaction was last modified by the user.
 * @property notes A free-text field modifiable by the user. Any &#39;word&#39; (whitespace separated), prefixed with a #, is considered a tag. These tags become searchable.
 * @property originalAmount The original amount that was received from the provider, before the user changed it.
 * @property originalDate The original date that was received from the provider, before the user changed it. The date cannot be older than 10 years.
 * @property originalDescription The original description that was received from the provider, before the user changed it.
 * @property payload Arbitrary metadata in key value format with strings, provided by the financial institution in question. It can be used either for deep-linking back to the financial institution&#39;s app, for displaying additional information about the transaction, or for backend purposes such as automatic categorization improvement. It can also include metadata generated by Tink, for example transfer transactions that are automatically flagged as transfers based on the identification of the corresponding transaction on the other account and which includes the primary identifier of the peer transaction for easy access. Max total size of payload data is 10M chars.
 * @property pending Indicates if this transaction has been settled or is still pending.
 * @property timestamp The timestamp of when the transaction was first saved to database.
 * @property type The type of the transaction.
 * @property userId The internal identifier of the user that the transaction belongs to.
 * @property upcoming Indicates if this is an upcoming transaction not booked yet.
 * @property currencyDenominatedAmount The amount of the transaction represented as a scale and unscaled value together with the ISO 4217 currency code of the amount. The amount can be modified by the user but not the currency code.
 * @property currencyDenominatedOriginalAmount The original amount that was received from the provider, before the user changed it. The amount is represented as a scale and unscaled value together with the ISO 4217 currency code of the amount.
 * @property parts Transaction parts (Beta). Available if the transaction is divided into more than one part.
 * @property partnerPayload The payload that was previously ingested on the Connector API.
 * @property dispensableAmount The dispensable amount of the transaction.
 * @property userModified
 */
@JsonClass(generateAdapter = true)
data class Transaction(
    @Json(name = "accountId") @field:Json(name = "accountId") var accountId: String,
    @Json(name = "amount") @field:Json(name = "amount") var amount: Double,
    @Json(name = "categoryId") @field:Json(name = "categoryId") var categoryId: String,
    @Json(name = "categoryType") @field:Json(name = "categoryType") var categoryType: Transaction.CategoryTypeEnum,
    @Json(name = "credentialsId") @field:Json(name = "credentialsId") var credentialsId: String,
    @Json(name = "date") @field:Json(name = "date") var date: ZonedDateTime,
    @Json(name = "description") @field:Json(name = "description") var description: String,
    @Json(name = "id") @field:Json(name = "id") var id: String,
    @Json(name = "lastModified") @field:Json(name = "lastModified") var lastModified: ZonedDateTime,
    @Json(name = "notes") @field:Json(name = "notes") var notes: String,
    @Json(name = "originalAmount") @field:Json(name = "originalAmount") var originalAmount: Double,
    @Json(name = "originalDate") @field:Json(name = "originalDate") var originalDate: ZonedDateTime,
    @Json(name = "originalDescription") @field:Json(name = "originalDescription") var originalDescription: String,
    @Json(name = "pending") @field:Json(name = "pending") var pending: Boolean,
    @Json(name = "timestamp") @field:Json(name = "timestamp") var timestamp: Long,
    @Json(name = "type") @field:Json(name = "type") var type: Transaction.TypeEnum,
    @Json(name = "userId") @field:Json(name = "userId") var userId: String,
    @Json(name = "payload") @field:Json(name = "payload") var payload: Map<String, String>? = null,
    @Json(name = "upcoming") @field:Json(name = "upcoming") var upcoming: Boolean? = null,
    @Json(name = "currencyDenominatedAmount") @field:Json(name = "currencyDenominatedAmount") var currencyDenominatedAmount: CurrencyDenominatedAmount? = null,
    @Json(name = "currencyDenominatedOriginalAmount") @field:Json(name = "currencyDenominatedOriginalAmount") var currencyDenominatedOriginalAmount: CurrencyDenominatedAmount? = null,
    @Json(name = "parts") @field:Json(name = "parts") var parts: List<TransactionPart>? = null,
    @Json(name = "partnerPayload") @field:Json(name = "partnerPayload") var partnerPayload: Map<String, Map<String, Any?>>? = null,
    @Json(name = "dispensableAmount") @field:Json(name = "dispensableAmount") var dispensableAmount: BigDecimal? = null,
    @Json(name = "userModified") @field:Json(name = "userModified") var userModified: Boolean? = null
) {
    /**
     * The category type of the transaction.
     * Values: INCOME, EXPENSES, TRANSFERS
     */
    @JsonClass(generateAdapter = false)
    enum class CategoryTypeEnum(val value: String) {
        @Json(name = "INCOME") INCOME("INCOME"),
        @Json(name = "EXPENSES") EXPENSES("EXPENSES"),
        @Json(name = "TRANSFERS") TRANSFERS("TRANSFERS")
    }
    /**
     * The type of the transaction.
     * Values: DEFAULT, CREDIT_CARD, TRANSFER, PAYMENT, WITHDRAWAL
     */
    @JsonClass(generateAdapter = false)
    enum class TypeEnum(val value: String) {
        @Json(name = "DEFAULT") DEFAULT("DEFAULT"),
        @Json(name = "CREDIT_CARD") CREDIT_CARD("CREDIT_CARD"),
        @Json(name = "TRANSFER") TRANSFER("TRANSFER"),
        @Json(name = "PAYMENT") PAYMENT("PAYMENT"),
        @Json(name = "WITHDRAWAL") WITHDRAWAL("WITHDRAWAL")
    }
}
