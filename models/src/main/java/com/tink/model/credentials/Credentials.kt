package com.tink.model.credentials

import android.os.Parcelable
import org.threeten.bp.Instant
import org.threeten.bp.temporal.ChronoUnit
import com.tink.model.authentication.ThirdPartyAppAuthentication
import com.tink.model.misc.Field
import com.tink.model.provider.Provider
import kotlinx.android.parcel.Parcelize

/**
 * This model represents how users are connected to a [Provider] to access their financial data.
 *
 * @property providerName Identifier for the [Provider]. See [Provider.name]
 * @property type The authentication type used for the credentials.
 * @property fields The map of [Field] name and value pairs for the [Provider].
 * @property id The unique identifier of the credentials.
 * @property status The current status of the credentials.
 * @property statusPayload A user-friendly text connected to the [Status]
 * @property supplementalInformation The list of [Field] objects representing additional information required for the authentication flow
 * @property statusUpdated Indicates when the credentials' status was last updated
 * @property updated Indicates the last time when the [status] was [Status.UPDATED]
 * @property sessionExpiryDate The session expiration time of the credentials for a [Provider] with [Provider.accessType] set to [Provider.AccessType.OPEN_BANKING]
 * @property thirdPartyAppAuthentication The [ThirdPartyAppAuthentication] object containing information about the third party authentication flow
 */
@Parcelize
data class Credentials(
    val providerName: String,
    val type: Type,
    val fields: Map<String, String>,
    val id: String,
    val status: Status? = null,
    val statusPayload: String? = null,
    val supplementalInformation: List<Field> = emptyList(),
    val statusUpdated: Instant = Instant.EPOCH,
    val updated: Instant = Instant.EPOCH,
    val sessionExpiryDate: Instant? = null,
    val thirdPartyAppAuthentication: ThirdPartyAppAuthentication? = null
) : Comparable<Credentials>, Parcelable {

    /**
     * Represents the type of authentication used for the credentials.
     */
    enum class Type {
        UNKNOWN,
        PASSWORD,
        MOBILE_BANKID,
        THIRD_PARTY_AUTHENTICATION,
        KEYFOB,
        FRAUD
    }

    /**
     * Represents the status of the credentials.
     *
     * When data is being fetched/updated from a [Provider], the status of the credentials can change to indicate the current state of the flow.
     * You can observe the credentials and act upon changes in the status if the [statusUpdated] value is greater than what it was previously.
     */
    enum class Status {
        UNKNOWN,
        CREATED,
        AUTHENTICATING,
        UPDATING,
        UPDATED,
        TEMPORARY_ERROR,
        AUTHENTICATION_ERROR,
        PERMANENT_ERROR,
        AWAITING_MOBILE_BANKID_AUTHENTICATION,
        AWAITING_THIRD_PARTY_APP_AUTHENTICATION,
        AWAITING_SUPPLEMENTAL_INFORMATION,
        SESSION_EXPIRED,
        DISABLED,
        DELETED
    }

    private fun isThirdPartyRefreshable(): Boolean =
        sessionExpiryDate?.let {
            // Only credentials of Open Banking connections has sessionExpiryDate.
            // Therefore, do the 7 day range check to see if it needs refreshing.
            ChronoUnit.DAYS.between(Instant.now(), it) <= 7
        } ?: true

    private fun manuallyRefreshableStatus(): Boolean {
        // Only allow the user to manually refresh BankID based credentials (as regular once will be automatically updated).
        return type == Type.MOBILE_BANKID && isBankIdRefreshable() ||
                type == Type.THIRD_PARTY_AUTHENTICATION &&
                isThirdPartyRefreshable()
    }

    private fun isBankIdRefreshable(): Boolean =
        when (status) {
            Status.AUTHENTICATION_ERROR,
            Status.TEMPORARY_ERROR,
            Status.CREATED,
            Status.UPDATED -> true
            else -> false
        }

    override fun compareTo(other: Credentials): Int = providerName.compareTo(other.providerName)
}
