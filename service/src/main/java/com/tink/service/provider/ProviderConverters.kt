package com.tink.service.provider

import com.tink.model.misc.Field
import com.tink.model.provider.Provider
import com.tink.service.credentials.toCoreModel
import com.tink.rest.models.ProviderListResponse
import com.tink.service.misc.toCoreModel
import com.tink.service.misc.toImages

internal typealias ProviderDTO = com.tink.rest.models.Provider
internal typealias ProviderStatusDTO = com.tink.rest.models.Provider.StatusEnum
internal typealias ProviderTypeDTO = com.tink.rest.models.Provider.TypeEnum
internal typealias ProviderAccessTypeDTO = com.tink.rest.models.Provider.AccessTypeEnum
internal typealias ProviderCapabilityDTO = com.tink.rest.models.Provider.CapabilitiesEnum

internal fun ProviderDTO.toCoreModel(): Provider =
    Provider(
        name = name,
        displayName = displayName,
        type = type.toProviderType(),
        status = status.toProviderStatus(),
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
        capabilities = capabilitiesOrEmpty()
    )

internal fun ProviderStatusDTO.toProviderStatus(): Provider.Status =
    when (this) {
        ProviderStatusDTO.DISABLED -> Provider.Status.DISABLED
        ProviderStatusDTO.ENABLED -> Provider.Status.ENABLED
        ProviderStatusDTO.TEMPORARY_DISABLED -> Provider.Status.TEMPORARY_DISABLED
    }

internal fun ProviderTypeDTO.toProviderType(): Provider.Type =
    when (this) {
        ProviderTypeDTO.BANK -> Provider.Type.BANK
        ProviderTypeDTO.BROKER -> Provider.Type.BROKER
        ProviderTypeDTO.CREDIT_CARD -> Provider.Type.CREDIT_CARD
        ProviderTypeDTO.OTHER -> Provider.Type.OTHER
        ProviderTypeDTO.TEST -> Provider.Type.TEST
        ProviderTypeDTO.FRAUD -> Provider.Type.FRAUD
    }

internal fun ProviderDTO.fieldsOrEmpty(): List<Field> =
    fields.map { it.toCoreModel() }

internal fun ProviderAccessTypeDTO.toAccessType(): Provider.AccessType =
    when (this) {
        ProviderAccessTypeDTO.OPEN_BANKING -> Provider.AccessType.OPEN_BANKING
        ProviderAccessTypeDTO.OTHER -> Provider.AccessType.OTHER
    }

internal fun ProviderDTO.capabilitiesOrEmpty(): List<Provider.Capability> =
    capabilities.map { it.toCoreModel() }

internal fun ProviderCapabilityDTO.toCoreModel(): Provider.Capability =
    when (this) {
        ProviderCapabilityDTO.UNKNOWN -> Provider.Capability.UNKNOWN
        ProviderCapabilityDTO.TRANSFERS -> Provider.Capability.TRANSFERS
        ProviderCapabilityDTO.EINVOICES -> Provider.Capability.EINVOICES
        ProviderCapabilityDTO.MORTGAGE_AGGREGATION -> Provider.Capability.MORTGAGE_AGGREGATION
        ProviderCapabilityDTO.CHECKING_ACCOUNTS -> Provider.Capability.CHECKING_ACCOUNTS
        ProviderCapabilityDTO.SAVINGS_ACCOUNTS -> Provider.Capability.SAVINGS_ACCOUNTS
        ProviderCapabilityDTO.CREDIT_CARDS -> Provider.Capability.CREDIT_CARDS
        ProviderCapabilityDTO.LOANS -> Provider.Capability.LOANS
        ProviderCapabilityDTO.INVESTMENTS -> Provider.Capability.INVESTMENTS
        ProviderCapabilityDTO.PAYMENTS -> Provider.Capability.PAYMENTS
        ProviderCapabilityDTO.IDENTITY_DATA -> Provider.Capability.IDENTITY_DATA
    }

internal fun ProviderListResponse.toProviderList() =
    providers
        ?.let { list -> list.map { it.toCoreModel() } }
        .orEmpty()