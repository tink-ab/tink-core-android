package com.tink.service.account

import androidx.annotation.VisibleForTesting
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
        identifiers = convertJsonStringArrayToList(identifiers),
        transferDestinations = transferDestinations?.mapNotNull { it.uri } ?: emptyList()
    )

internal fun AccountTypeDto.toCoreModel() =
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

internal fun Account.Type.toDto(): AccountTypeDto? =
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

internal fun UpdateAccountDescriptor.toRequest() =
    UpdateAccountRequest(
        name = name,
        excluded = excluded,
        favored = favored,
        ownership = ownership?.toDouble(),
        type = type?.toDto()
    )

/**
 *   Converts a Json string containing a list of string to an actual string list
 */
@VisibleForTesting
internal fun convertJsonStringArrayToList(rawString: String?): List<String> =
    rawString
        ?.replace("[", "")
        ?.replace("]", "")
        ?.split(",")
        ?.map { it.replace("\"", "") }
        ?: emptyList()
