package com.tink.service.transfer

import com.tink.rest.apis.TransferApi
import com.tink.rest.models.Transfer
import java.math.BigDecimal
import javax.inject.Inject

interface TransferService {
    suspend fun createTransfer()
}

class TransferServiceImpl @Inject constructor(
    private val transferApi: TransferApi
) : TransferService {

    override suspend fun createTransfer() {
        transferApi.createTransfer(
            Transfer(
                amount = 10.0,
                credentialsId = "test",
                currency = "SEK",
                destinationMessage = "test",
                destinationUri = Transfer.DestinationUriEnum.SEPAMINUSEUR,
                sourceMessage = "henlo",
                sourceUri = "tink://123412341234"

            )
        )
    }
}