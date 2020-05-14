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
        images = imageUrls.toImages(),
        identifier = stripIdentifier(identifiers),
        transferDestinations = transferDestinations?.mapNotNull { it.uri } ?: emptyList()
    )

fun AccountTypeDto.toCoreModel() =
    when (this) {
        AccountTypeDto.CHECKING -> Account.Type.CHECKING
        AccountTypeDto.SAVINGS -> Account.Type.SAVINGS
        AccountTypeDto.INVESTMENT -> Account.Type.INVESTMENT
        AccountTypeDto.MORTGAGE -> Account.Type.MORTGAGE
        AccountTypeDto.CREDIT_CARD -> Account.Type.CREDIT_CARD
        AccountTypeDto.LOAN -> Account.Type.LOAN
        AccountTypeDto.PENSION -> Account.Type.PENSION
        AccountTypeDto.OTHER -> Account.Type.OTHER
        AccountTypeDto.EXTERNAL -> Account.Type.EXTERNAL
    }

fun Account.Type.toDto(): AccountTypeDto? =
    when (this) {
        Account.Type.CHECKING -> AccountTypeDto.CHECKING
        Account.Type.SAVINGS -> AccountTypeDto.SAVINGS
        Account.Type.INVESTMENT -> AccountTypeDto.INVESTMENT
        Account.Type.MORTGAGE -> AccountTypeDto.MORTGAGE
        Account.Type.CREDIT_CARD -> AccountTypeDto.CREDIT_CARD
        Account.Type.LOAN -> AccountTypeDto.LOAN
        Account.Type.PENSION -> AccountTypeDto.PENSION
        Account.Type.OTHER -> AccountTypeDto.OTHER
        Account.Type.EXTERNAL -> AccountTypeDto.EXTERNAL
    }

fun UpdateAccountDescriptor.toRequest() =
    UpdateAccountRequest(
        name = name,
        excluded = excluded,
        favored = favored,
        ownership = ownership?.toDouble(),
        type = type?.toDto()
    )

fun stripIdentifier(identifiersRaw: String?): String? =
    identifiersRaw
        ?.replace("[", "")
        ?.replace("]", "")
        ?.split(",")
        ?.firstOrNull()
        ?.replace("\"", "")
