package com.tink.model.account

import android.os.Parcelable
import com.tink.model.misc.ExactNumber
import kotlinx.android.parcel.Parcelize
import org.threeten.bp.Instant

@Parcelize
data class AccountDetails(
    val interest: ExactNumber? = null,
    val numberOfMonthsBound: Int? = null,
    val type: Type?,
    val nextDayOfTermsChange: Instant? = null
) : Parcelable {

    enum class Type {
        UNKNOWN,
        MORTGAGE,
        BLANCO,
        MEMBERSHIP,
        VEHICLE,
        LAND,
        STUDENT,
        CREDIT,
        OTHER
    }
}
