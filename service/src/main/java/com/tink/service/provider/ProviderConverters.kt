package com.tink.service.provider

import com.tink.model.misc.Field
import com.tink.model.provider.Provider
import com.tink.service.credentials.toCoreModel
import com.tink.service.generated.models.ProviderListResponse
import com.tink.service.misc.toCoreModel
import com.tink.service.misc.toImages

internal typealias ProviderDTO = com.tink.service.generated.models.Provider
internal typealias ProviderStatusDTO = com.tink.service.generated.models.Provider.StatusEnum
internal typealias ProviderTypeDTO = com.tink.service.generated.models.Provider.TypeEnum
internal typealias ProviderAccessTypeDTO = com.tink.service.generated.models.Provider.AccessTypeEnum

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
        images = images?.toImages(),
        financialInstitution = Provider.FinancialInstitution(
            financialInstitutionId,
            financialInstitutionName
        ),
        accessType = this.accessType.toAccessType()
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

internal fun ProviderListResponse.toProviderList() =
    providers
        ?.let { list -> list.map { it.toCoreModel() } }
        .orEmpty()