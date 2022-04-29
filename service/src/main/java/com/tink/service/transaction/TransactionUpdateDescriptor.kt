package com.tink.service.transaction

import com.tink.model.transaction.Tag
import java.time.Instant

data class TransactionUpdateDescriptor(
    val transactionId: String,
    val description: String? = null,
    val date: Instant? = null,
    val notes: String? = null,
    val tags: List<Tag>? = null
)
