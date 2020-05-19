package com.tink.service.transfer

import com.tink.model.misc.Amount

data class CreateTransferDescriptor(
    val amount: Amount,
    val sourceUri: String,
    val sourceMessage: String?,
    val destinationUri: String,
    val destinationMessage: String,
    val credentialsId: String? = null
)
