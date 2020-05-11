package com.tink.service.transfer

import com.tink.model.misc.Amount

data class CreateTransferDescriptor(
    val credentialsId: String,
    val amount: Amount,
    val sourceUri: String,
    val sourceMessage: String,
    val destinationUri: String,
    val destinationMessage: String
)
