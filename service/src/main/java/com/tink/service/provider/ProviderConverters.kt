package com.tink.service.provider

import com.tink.model.misc.Field
import com.tink.model.provider.Provider
import com.tink.rest.models.ProviderListResponse
import com.tink.service.credentials.toCoreModel
import com.tink.service.misc.toCoreModel
import com.tink.service.misc.toImages

internal typealias ProviderDto = com.tink.rest.models.Provider
internal typealias ProviderStatusDto = com.tink.rest.models.Provider.StatusEnum
internal typealias ProviderReleaseStatusDto = com.tink.rest.models.Provider.ReleaseStatusEnum
internal typealias ProviderTypeDto = com.tink.rest.models.Provider.TypeEnum
internal typealias ProviderAccessTypeDto = com.tink.rest.models.Provider.AccessTypeEnum
internal typealias ProviderCapabilityDto = com.tink.rest.models.Provider.CapabilitiesEnum
internal typealias ProviderAuthenticationUserTypeDto = com.tink.rest.models.Provider.AuthenticationUserTypeEnum

internal fun ProviderDto.toCoreModel(): Provider =
    Provider(
        name = name,
        displayName = displayName,
        type = type.toProviderType(),
        status = status.toProviderStatus(),
        releaseStatus = releaseStatus?.toProviderReleaseStatus(),
        credentialsType = credentialsType.toCoreModel(),
        helpText = passwordHelpText.orEmpty(),
        isPopular = popular,
        fields = fieldsOrEmpty(),
        groupDisplayName = groupDisplayName.orEmpty(),
        displayDescription = displayDescription.orEmpty(),
        marketCode = market,
        images = images?.toImages(),
        financialInstitution = Provider.FinancialInstitution(
            financialInstitutionId,
            financialInstitutionName
        ),
        accessType = this.accessType.toAccessType(),
        capabilities = capabilitiesOrEmpty(),
        authenticationUserType = authenticationUserType.toCoreModel()
    )

internal fun ProviderStatusDto.toProviderStatus(): Provider.Status =
    when (this) {
        ProviderStatusDto.DISABLED -> Provider.Status.DISABLED
        ProviderStatusDto.ENABLED -> Provider.Status.ENABLED
        ProviderStatusDto.TEMPORARY_DISABLED -> Provider.Status.TEMPORARY_DISABLED
        ProviderStatusDto.UNKNOWN -> Provider.Status.UNKNOWN
    }

internal fun ProviderReleaseStatusDto.toProviderReleaseStatus(): Provider.ReleaseStatus =
    when (this) {
        ProviderReleaseStatusDto.UNKNOWN -> Provider.ReleaseStatus.UNKNOWN
        ProviderReleaseStatusDto.BETA -> Provider.ReleaseStatus.BETA
    }

internal fun ProviderTypeDto.toProviderType(): Provider.Type =
    when (this) {
        ProviderTypeDto.BANK -> Provider.Type.BANK
        ProviderTypeDto.BROKER -> Provider.Type.BROKER
        ProviderTypeDto.CREDIT_CARD -> Provider.Type.CREDIT_CARD
        ProviderTypeDto.OTHER -> Provider.Type.OTHER
        ProviderTypeDto.TEST -> Provider.Type.TEST
        ProviderTypeDto.FRAUD -> Provider.Type.FRAUD
    }

internal fun ProviderDto.fieldsOrEmpty(): List<Field> =
    fields.map { it.toCoreModel() }

internal fun ProviderAccessTypeDto.toAccessType(): Provider.AccessType =
    when (this) {
        ProviderAccessTypeDto.OPEN_BANKING -> Provider.AccessType.OPEN_BANKING
        ProviderAccessTypeDto.OTHER -> Provider.AccessType.OTHER
    }

internal fun ProviderDto.capabilitiesOrEmpty(): List<Provider.Capability> =
    capabilities.map { it.toCoreModel() }

internal fun ProviderCapabilityDto.toCoreModel(): Provider.Capability =
    when (this) {
        ProviderCapabilityDto.UNKNOWN -> Provider.Capability.UNKNOWN
        ProviderCapabilityDto.TRANSFERS -> Provider.Capability.TRANSFERS
        ProviderCapabilityDto.EINVOICES -> Provider.Capability.EINVOICES
        ProviderCapabilityDto.MORTGAGE_AGGREGATION -> Provider.Capability.MORTGAGE_AGGREGATION
        ProviderCapabilityDto.CHECKING_ACCOUNTS -> Provider.Capability.CHECKING_ACCOUNTS
        ProviderCapabilityDto.SAVINGS_ACCOUNTS -> Provider.Capability.SAVINGS_ACCOUNTS
        ProviderCapabilityDto.CREDIT_CARDS -> Provider.Capability.CREDIT_CARDS
        ProviderCapabilityDto.LOANS -> Provider.Capability.LOANS
        ProviderCapabilityDto.INVESTMENTS -> Provider.Capability.INVESTMENTS
        ProviderCapabilityDto.PAYMENTS -> Provider.Capability.PAYMENTS
        ProviderCapabilityDto.IDENTITY_DATA -> Provider.Capability.IDENTITY_DATA
    }

internal fun ProviderAuthenticationUserTypeDto.toCoreModel(): Provider.AuthenticationUserType =
    when (this) {
        ProviderAuthenticationUserTypeDto.UNKNOWN -> Provider.AuthenticationUserType.UNKNOWN
        ProviderAuthenticationUserTypeDto.PERSONAL -> Provider.AuthenticationUserType.PERSONAL
        ProviderAuthenticationUserTypeDto.BUSINESS -> Provider.AuthenticationUserType.BUSINESS
        ProviderAuthenticationUserTypeDto.CORPORATE -> Provider.AuthenticationUserType.CORPORATE
    }

internal fun ProviderListResponse.toProviderList() =
    providers
        ?.let { list -> list.map { it.toCoreModel() } }
        .orEmpty()
