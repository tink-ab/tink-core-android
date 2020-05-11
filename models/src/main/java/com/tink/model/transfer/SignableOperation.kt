package com.tink.model.transfer

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.threeten.bp.Instant

@Parcelize
data class SignableOperation(
    val id: String,
    val credentialsId: String,
    val created: Instant,
    val status: Status,
    val statusMessage: String,
    val type: Type,
    val underlyingId: String,
    val updated: Instant,
    val userId: String
) : Parcelable {

    enum class Status {
        CREATED,
        EXECUTING,
        AWAITING_CREDENTIALS,
        CANCELLED,
        FAILED,
        EXECUTED,
        AWAITING_THIRD_PARTY_APP_AUTHENTICATION
    }

    enum class Type {
        TRANSFER
    }
}
