package com.tink.service.account

import androidx.annotation.VisibleForTesting
import com.tink.model.account.Account
import com.tink.model.account.AccountDetails
import com.tink.rest.apis.UpdateAccountRequest
import com.tink.service.misc.toAmount
import com.tink.service.misc.toDouble
import com.tink.service.misc.toExactNumber
import com.tink.service.misc.toInstant
import com.tink.rest.models.Account as AccountDto
import com.tink.rest.models.Account.AccountExclusionEnum as AccountExclusionDto
import com.tink.rest.models.Account.FlagsEnum as AccountFlagsDto
import com.tink.rest.models.Account.TypeEnum as AccountTypeDto
import com.tink.rest.models.AccountDetails as AccountDetailsDto
import com.tink.rest.models.AccountDetails.TypeEnum as AccountDetailsTypeDto

fun AccountDto.toCoreModel(): Account =
    Account(
        id = id,
        accountNumber = accountNumber,
        balance = currencyDenominatedBalance?.toAmount()!!,
        credentialsId = credentialsId,
        excluded = excluded,
        favored = favored,
        closed = closed ?: false,
        name = name,
        holderName = holderName,
        accountDetails = details?.toCoreModel(),
        ownership = ownership.toExactNumber(),
        type = type.toCoreModel(),
        flags = flagsOrEmpty(),
        accountExclusion = accountExclusion.toCoreModel(),
        refreshed = refreshed.toInstant(),
        financialInstitutionID = financialInstitutionId,
        identifiers = convertJsonStringArrayToList(identifiers),
        transferDestinations = transferDestinations?.mapNotNull { it.uri } ?: emptyList(),
        firstSeen = firstSeen
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
        else -> Account.Type.UNKNOWN
    }

internal fun Account.Type.toDto(): AccountTypeDto =
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
        else -> AccountTypeDto.UNKNOWN
    }

internal fun AccountExclusionDto.toCoreModel() =
    when (this) {
        AccountExclusionDto.AGGREGATION -> Account.AccountExclusion.AGGREGATION
        AccountExclusionDto.PFM_AND_SEARCH -> Account.AccountExclusion.PFM_AND_SEARCH
        AccountExclusionDto.PFM_DATA -> Account.AccountExclusion.PFM_DATA
        AccountExclusionDto.NONE -> Account.AccountExclusion.NONE
        else -> Account.AccountExclusion.UNKNOWN
    }

internal fun AccountDto.flagsOrEmpty(): List<Account.Flags> =
    flags
        ?.takeIf { it.isNotEmpty() }
        ?.let { value ->
            convertJsonStringArrayToList(value).map { it.toFlag() }
        }
        ?: listOf()

private fun String.toFlag() =
    when {
        this == AccountFlagsDto.BUSINESS.value -> Account.Flags.BUSINESS
        this == AccountFlagsDto.MANDATE.value -> Account.Flags.MANDATE
        else -> Account.Flags.UNKNOWN
    }

internal fun AccountFlagsDto.toCoreModel() =
    when (this) {
        AccountFlagsDto.BUSINESS -> Account.Flags.BUSINESS
        AccountFlagsDto.MANDATE -> Account.Flags.MANDATE
        else -> Account.Flags.UNKNOWN
    }

internal fun AccountDetailsDto.toCoreModel(): AccountDetails =
    AccountDetails(
        interest = interest?.toExactNumber(),
        numberOfMonthsBound = numMonthsBound,
        type = type?.toCoreModel(),
        nextDayOfTermsChange = nextDayOfTermsChange?.toInstant()
    )

internal fun AccountDetailsTypeDto.toCoreModel(): AccountDetails.Type =
    when (this) {
        AccountDetailsTypeDto.MORTGAGE -> AccountDetails.Type.MORTGAGE
        AccountDetailsTypeDto.BLANCO -> AccountDetails.Type.BLANCO
        AccountDetailsTypeDto.MEMBERSHIP -> AccountDetails.Type.MEMBERSHIP
        AccountDetailsTypeDto.VEHICLE -> AccountDetails.Type.VEHICLE
        AccountDetailsTypeDto.LAND -> AccountDetails.Type.LAND
        AccountDetailsTypeDto.STUDENT -> AccountDetails.Type.STUDENT
        AccountDetailsTypeDto.CREDIT -> AccountDetails.Type.CREDIT
        AccountDetailsTypeDto.OTHER -> AccountDetails.Type.OTHER
        else -> AccountDetails.Type.UNKNOWN
    }

internal fun UpdateAccountDescriptor.toRequest() =
    UpdateAccountRequest(
        name = name,
        favored = favored,
        ownership = ownership?.toDouble(),
        type = type?.toDto(),
        accountExclusion = getAccountExclusionEnumFromBoolean(accountExclusion)
    )

private fun getAccountExclusionEnumFromBoolean(
    excluded: Boolean?
): AccountExclusionDto =
    if (excluded == true) {
        AccountExclusionDto.PFM_AND_SEARCH
    } else {
        AccountExclusionDto.NONE
    }

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
