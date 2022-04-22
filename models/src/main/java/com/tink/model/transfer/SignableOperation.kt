package com.tink.model.transfer

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.time.Instant

@Parcelize
data class SignableOperation(
    val id: String,
    val credentialsId: String?,
    val created: Instant,
    val status: Status,
    val statusMessage: String,
    val type: Type,
    val underlyingId: String,
    val updated: Instant,
    val userId: String
) : Parcelable {

    /**
     * The status of the operation. [CANCELLED], [FAILED] and [EXECUTED] are all endstates.
     */
    enum class Status {
        CREATED,
        EXECUTING,
        AWAITING_CREDENTIALS,
        CANCELLED,
        FAILED,
        EXECUTED,
        AWAITING_THIRD_PARTY_APP_AUTHENTICATION,
        UNKNOWN;

        fun isEndState() =
            when (this) {
                CREATED,
                EXECUTING,
                AWAITING_CREDENTIALS,
                AWAITING_THIRD_PARTY_APP_AUTHENTICATION,
                UNKNOWN -> false
                CANCELLED,
                FAILED,
                EXECUTED -> true
            }
    }

    enum class Type {
        TRANSFER, UNKNOWN
    }
}
