/**
 * NOTE: This class is auto generated by the Swagger Gradle Codegen for the following API: Tink API
 *
 * More info on this tool is available on https://github.com/Yelp/swagger-gradle-codegen
 */

package com.tink.rest.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * The provider model represents financial institutions to where Tink can connect. It specifies how Tink accesses the financial institution, metadata about the financialinstitution, and what financial information that can be accessed.
 * @property accessType What Tink uses to access the data.
 * @property capabilities Indicates what this provider is capable of, in terms of financial data it can aggregate and if it can execute payments.
 * @property credentialsType When creating a new credential connected to the provider this will be the credentials type.
 * @property authenticationUserType The type of authentication to use
 * @property currency The default currency of the provider.
 * @property displayName The display name of the provider.
 * @property fields List of fields which need to be provided when creating a credential connected to the provider.
 * @property financialInstitutionId A unique identifier to group providers belonging the same financial institution.
 * @property financialInstitutionName The name of the financial institution.
 * @property market The market of the provider. Each provider is unique per market.
 * @property multiFactor Indicates if the provider requires multi-factor authentication.
 * @property name The unique identifier of the provider. This is used when creating new credentials.
 * @property popular Indicates if the provider is popular. This is normally set to true for the biggest financial institutions on a market.
 * @property status Indicates the current status of the provider. It is only possible to perform credentials create or refresh actions on providers which are enabled.
 * @property releaseStatus Indicates the release status of the provider
 * @property transactional Indicates if Tink can aggregate transactions for this provider.
 * @property type Indicates what type of financial institution the provider represents.
 * @property authenticationFlow For providers with access type &#x60;OPEN_BANKING&#x60;, indicates what type of authentication flow is used to access the data.
 * @property displayDescription Short displayable description of the authentication type used.
 * @property groupDisplayName A display name for providers which are branches of a bigger group.
 * @property images Image urls for the provider.
 * @property passwordHelpText Short description of how to authenticate when creating a new credential for connected to the provider.
 */
@JsonClass(generateAdapter = true)
data class Provider(
    @Json(name = "accessType") @field:Json(name = "accessType") var accessType: Provider.AccessTypeEnum,
    @Json(name = "capabilities") @field:Json(name = "capabilities") var capabilities: List<Provider.CapabilitiesEnum>,
    @Json(name = "credentialsType") @field:Json(name = "credentialsType") var credentialsType: Credentials.TypeEnum,
    @Json(name = "authenticationUserType") @field:Json(name = "authenticationUserType") var authenticationUserType: AuthenticationUserTypeEnum,
    @Json(name = "currency") @field:Json(name = "currency") var currency: String,
    @Json(name = "displayName") @field:Json(name = "displayName") var displayName: String,
    @Json(name = "fields") @field:Json(name = "fields") var fields: List<Field>,
    @Json(name = "financialInstitutionId") @field:Json(name = "financialInstitutionId") var financialInstitutionId: String,
    @Json(name = "financialInstitutionName") @field:Json(name = "financialInstitutionName") var financialInstitutionName: String,
    @Json(name = "market") @field:Json(name = "market") var market: String,
    @Json(name = "multiFactor") @field:Json(name = "multiFactor") var multiFactor: Boolean,
    @Json(name = "name") @field:Json(name = "name") var name: String,
    @Json(name = "popular") @field:Json(name = "popular") var popular: Boolean,
    @Json(name = "status") @field:Json(name = "status") var status: Provider.StatusEnum,
    @Json(name = "releaseStatus") @field:Json(name = "releaseStatus") var releaseStatus: Provider.ReleaseStatusEnum? = null,
    @Json(name = "transactional") @field:Json(name = "transactional") var transactional: Boolean,
    @Json(name = "type") @field:Json(name = "type") var type: Provider.TypeEnum,
    @Json(name = "authenticationFlow") @field:Json(name = "authenticationFlow") var authenticationFlow: Provider.AuthenticationFlowEnum? = null,
    @Json(name = "displayDescription") @field:Json(name = "displayDescription") var displayDescription: String? = null,
    @Json(name = "groupDisplayName") @field:Json(name = "groupDisplayName") var groupDisplayName: String? = null,
    @Json(name = "images") @field:Json(name = "images") var images: ImageUrls? = null,
    @Json(name = "passwordHelpText") @field:Json(name = "passwordHelpText") var passwordHelpText: String? = null
) {
    /**
     * What Tink uses to access the data.
     * Values: OPEN_BANKING, OTHER
     */
    @JsonClass(generateAdapter = false)
    enum class AccessTypeEnum(val value: String) {
        @Json(name = "OPEN_BANKING") OPEN_BANKING("OPEN_BANKING"),
        @Json(name = "OTHER") OTHER("OTHER")
    }
    /**
     * For providers with access type `OPEN_BANKING`, indicates what type of authentication flow is used to access the data.
     * Values: EMBEDDED, REDIRECT, DECOUPLED
     */
    @JsonClass(generateAdapter = false)
    enum class AuthenticationFlowEnum(val value: String) {
        @Json(name = "UNKNOWN") UNKNOWN("UNKNOWN"),
        @Json(name = "EMBEDDED") EMBEDDED("EMBEDDED"),
        @Json(name = "REDIRECT") REDIRECT("REDIRECT"),
        @Json(name = "DECOUPLED") DECOUPLED("DECOUPLED")
    }
    /**
     * Indicates what this provider is capable of, in terms of financial data it can aggregate and if it can execute payments.
     * Values: UNKNOWN, TRANSFERS, EINVOICES, MORTGAGE_AGGREGATION, CHECKING_ACCOUNTS, SAVINGS_ACCOUNTS, CREDIT_CARDS, LOANS, INVESTMENTS, PAYMENTS, IDENTITY_DATA
     */
    @JsonClass(generateAdapter = false)
    enum class CapabilitiesEnum(val value: String) {
        @Json(name = "UNKNOWN") UNKNOWN("UNKNOWN"),
        @Json(name = "TRANSFERS") TRANSFERS("TRANSFERS"),
        @Json(name = "EINVOICES") EINVOICES("EINVOICES"),
        @Json(name = "MORTGAGE_AGGREGATION") MORTGAGE_AGGREGATION("MORTGAGE_AGGREGATION"),
        @Json(name = "CHECKING_ACCOUNTS") CHECKING_ACCOUNTS("CHECKING_ACCOUNTS"),
        @Json(name = "SAVINGS_ACCOUNTS") SAVINGS_ACCOUNTS("SAVINGS_ACCOUNTS"),
        @Json(name = "CREDIT_CARDS") CREDIT_CARDS("CREDIT_CARDS"),
        @Json(name = "LOANS") LOANS("LOANS"),
        @Json(name = "INVESTMENTS") INVESTMENTS("INVESTMENTS"),
        @Json(name = "PAYMENTS") PAYMENTS("PAYMENTS"),
        @Json(name = "IDENTITY_DATA") IDENTITY_DATA("IDENTITY_DATA")
    }
    /**
     * Indicates the current status of the provider. It is only possible to perform credentials create or refresh actions on providers which are enabled.
     * Values: ENABLED, TEMPORARY_DISABLED, DISABLED
     */
    @JsonClass(generateAdapter = false)
    enum class StatusEnum(val value: String) {
        @Json(name = "UNKNOWN") UNKNOWN("UNKNOWN"),
        @Json(name = "ENABLED") ENABLED("ENABLED"),
        @Json(name = "TEMPORARY_DISABLED") TEMPORARY_DISABLED("TEMPORARY_DISABLED"),
        @Json(name = "DISABLED") DISABLED("DISABLED")
    }
    /**
     * Indicates the current release status of the provider
     * Values: BETA
     */
    @JsonClass(generateAdapter = false)
    enum class ReleaseStatusEnum(val value: String) {
        @Json(name = "UNKNOWN") UNKNOWN("UNKNOWN"),
        @Json(name = "BETA") BETA("BETA")
    }
    /**
     * Indicates what type of financial institution the provider represents.
     * Values: BANK, CREDIT_CARD, BROKER, TEST, OTHER
     */
    @JsonClass(generateAdapter = false)
    enum class TypeEnum(val value: String) {
        @Json(name = "BANK") BANK("BANK"),
        @Json(name = "CREDIT_CARD") CREDIT_CARD("CREDIT_CARD"),
        @Json(name = "BROKER") BROKER("BROKER"),
        @Json(name = "TEST") TEST("TEST"),
        @Json(name = "FRAUD") FRAUD("FRAUD"),
        @Json(name = "OTHER") OTHER("OTHER")
    }

    /**
     * Indicates if a user authenticates toward the bank as a person or a business.
     * Values: PERSONAL, BUSINESS, CORPORATE
     */
    @JsonClass(generateAdapter = false)
    enum class AuthenticationUserTypeEnum(val value: String) {
        @Json(name = "UNKNOWN") UNKNOWN("UNKNOWN"),
        @Json(name = "PERSONAL") PERSONAL("PERSONAL"),
        @Json(name = "BUSINESS") BUSINESS("BUSINESS"),
        @Json(name = "CORPORATE") CORPORATE("CORPORATE")
    }
}
