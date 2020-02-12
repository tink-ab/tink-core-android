package com.tink.model.account

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import com.tink.model.Images
import com.tink.model.misc.Amount
import com.tink.model.misc.ExactNumber

@Parcelize
data class Account(
    val accountNumber: String,
    val balance: Amount,
    val credentialId: String,
    val excluded: Boolean,
    val favored: Boolean,
    val transactional: Boolean,
    val closed: Boolean,
    val id: String,
    val name: String,
    val ownership: ExactNumber,
    val type: Type,
    val images: Images?
) : Parcelable {

    enum class Type {
        TYPE_UNKNOWN,
        TYPE_CHECKING,
        TYPE_SAVINGS,
        TYPE_INVESTMENT,
        TYPE_MORTGAGE,
        TYPE_CREDIT_CARD,
        TYPE_LOAN,
        TYPE_DUMMY,
        TYPE_PENSION,
        TYPE_OTHER,
        TYPE_EXTERNAL
    }
}