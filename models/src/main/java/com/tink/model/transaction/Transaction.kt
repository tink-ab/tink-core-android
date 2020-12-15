package com.tink.model.transaction

import android.os.Parcelable
import com.tink.model.misc.Amount
import kotlinx.android.parcel.Parcelize
import org.threeten.bp.Instant

@Parcelize
data class Transaction(
    val id: String,
    val amount: Amount,
    val description: String,
    val categoryId: String,
    val date: Instant,
    val accountId: String,
    val notes: String,
    val tags: List<Tag>,
    val upcoming: Boolean,
    val pending: Boolean,
    val originalDate: Instant,
    val originalDescription: String,
    val originalAmount: Amount,
    val inserted: Instant
) : Parcelable

@Parcelize
data class Tag(val name: String) : Parcelable
