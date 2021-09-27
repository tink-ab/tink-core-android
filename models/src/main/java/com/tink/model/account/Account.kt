package com.tink.model.account

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import com.tink.model.misc.Amount
import com.tink.model.misc.ExactNumber
import org.threeten.bp.Instant

@Parcelize
data class Account(
    val accountNumber: String,
    val balance: Amount,
    val credentialsId: String,
    val excluded: Boolean,
    val favored: Boolean,
    val closed: Boolean,
    val id: String,
    val name: String,
    val holderName: String? = null,
    val accountDetails: AccountDetails? = null,
    val ownership: ExactNumber,
    val type: Type,
    val flags: List<Flags>,
    val accountExclusion: AccountExclusion,
    val refreshed: Instant = Instant.EPOCH,
    val financialInstitutionID: String? = null,
    val identifiers: List<String>,
    val transferDestinations: List<String>,
    val firstSeen: Long? = null
) : Parcelable {

    enum class Type {
        UNKNOWN,
        CHECKING,
        SAVINGS,
        INVESTMENT,
        MORTGAGE,
        CREDIT_CARD,
        LOAN,
        PENSION,
        OTHER,
        EXTERNAL
    }

    enum class AccountExclusion {
        UNKNOWN,
        AGGREGATION,
        PFM_AND_SEARCH,
        PFM_DATA,
        NONE
    }

    enum class Flags {
        UNKNOWN,
        BUSINESS,
        MANDATE
    }
}
