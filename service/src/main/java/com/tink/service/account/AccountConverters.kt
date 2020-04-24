package com.tink.service.account

import com.tink.model.account.Account
import com.tink.rest.apis.UpdateAccountRequest
import com.tink.service.misc.toAmount
import com.tink.service.misc.toDouble
import com.tink.service.misc.toExactNumber
import com.tink.service.misc.toImages
import com.tink.rest.models.Account as AccountDto
import com.tink.rest.models.Account.TypeEnum as AccountTypeDto

fun AccountDto.toCoreModel(): Account =
    Account(
        id = id,
        accountNumber = accountNumber,
        balance = currencyDenominatedBalance?.toAmount()!!,
        credentialsId = credentialsId,
        excluded = excluded,
        favored = favored,
        transactional = false, // TODO
        closed = closed ?: false,
        name = name,
        ownership = ownership.toExactNumber(),
        type = type.toCoreModel(),
        images = imageUrls.toImages()
    )

fun AccountTypeDto.toCoreModel() =
    when (this) {
        AccountTypeDto.CHECKING -> Account.Type.TYPE_CHECKING
        AccountTypeDto.SAVINGS -> Account.Type.TYPE_SAVINGS
        AccountTypeDto.INVESTMENT -> Account.Type.TYPE_INVESTMENT
        AccountTypeDto.MORTGAGE -> Account.Type.TYPE_MORTGAGE
        AccountTypeDto.CREDIT_CARD -> Account.Type.TYPE_CREDIT_CARD
        AccountTypeDto.LOAN -> Account.Type.TYPE_LOAN
        AccountTypeDto.PENSION -> Account.Type.TYPE_PENSION
        AccountTypeDto.OTHER -> Account.Type.TYPE_OTHER
        AccountTypeDto.EXTERNAL -> Account.Type.TYPE_EXTERNAL
    }

fun Account.Type.toDto(): AccountTypeDto? =
    when (this) {
        Account.Type.TYPE_CHECKING -> AccountTypeDto.CHECKING
        Account.Type.TYPE_SAVINGS -> AccountTypeDto.SAVINGS
        Account.Type.TYPE_INVESTMENT -> AccountTypeDto.INVESTMENT
        Account.Type.TYPE_MORTGAGE -> AccountTypeDto.MORTGAGE
        Account.Type.TYPE_CREDIT_CARD -> AccountTypeDto.CREDIT_CARD
        Account.Type.TYPE_LOAN -> AccountTypeDto.LOAN
        Account.Type.TYPE_PENSION -> AccountTypeDto.PENSION
        Account.Type.TYPE_OTHER -> AccountTypeDto.OTHER
        Account.Type.TYPE_EXTERNAL -> AccountTypeDto.EXTERNAL
        Account.Type.TYPE_UNKNOWN,
        Account.Type.TYPE_DUMMY -> null
    }

fun UpdateAccountDescriptor.toRequest() =
    UpdateAccountRequest(
        name = name,
        excluded = excluded,
        favored = favored,
        ownership = ownership?.toDouble(),
        type = type?.toDto()

    )
