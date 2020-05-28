package com.tink.service.transfer

import com.tink.model.misc.Amount

data class CreateTransferDescriptor(
    val amount: Amount,
    val sourceAccountUri: String,
    val sourceMessage: String?,
    val beneficiaryUri: String,
    val destinationMessage: String,
    val credentialsId: String? = null
)
