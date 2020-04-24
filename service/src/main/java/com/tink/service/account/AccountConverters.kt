package com.tink.service.account

import com.tink.model.account.Account
import com.tink.service.misc.toAmount
import com.tink.service.misc.toExactNumber
import com.tink.service.misc.toImages
import com.tink.rest.models.Account as AccountDTO
import com.tink.rest.models.Account.TypeEnum as AccountTypeDTO


fun AccountDTO.toCoreModel(): Account =
    Account(
        id = id,
        accountNumber = accountNumber,
        balance = currencyDenominatedBalance?.toAmount()!!,
        credentialsId = credentialsId,
        excluded = excluded,
        favored = favored,
        transactional = false, //TODO
        closed = closed ?: false,
        name = name,
        ownership = ownership.toExactNumber(),
        type = type.toCoreModel(),
        images = imageUrls.toImages()
    )


fun AccountTypeDTO.toCoreModel() =
    when (this) {
        AccountTypeDTO.CHECKING -> Account.Type.TYPE_CHECKING
        AccountTypeDTO.SAVINGS -> Account.Type.TYPE_SAVINGS
        AccountTypeDTO.INVESTMENT -> Account.Type.TYPE_INVESTMENT
        AccountTypeDTO.MORTGAGE -> Account.Type.TYPE_MORTGAGE
        AccountTypeDTO.CREDIT_CARD -> Account.Type.TYPE_CREDIT_CARD
        AccountTypeDTO.LOAN -> Account.Type.TYPE_LOAN
        AccountTypeDTO.PENSION -> Account.Type.TYPE_PENSION
        AccountTypeDTO.OTHER -> Account.Type.TYPE_OTHER
        AccountTypeDTO.EXTERNAL -> Account.Type.TYPE_EXTERNAL
    }
