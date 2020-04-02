package com.tink.service.credentials

import com.tink.model.credentials.Credentials
import org.threeten.bp.Instant
import com.tink.service.generated.models.Credentials as CredentialsRestDTO

fun CredentialsRestDTO.toCoreModel(): Credentials {

    fun Long?.toInstant() = Instant.ofEpochMilli(this ?: 0)

    return Credentials(
        id = id!!,
        providerName = providerName,
        fields = fields,
        supplementalInformation = listOf(), // TODO: rest setup
        sessionExpiryDate = sessionExpiryDate.toInstant(),
        status = status!!.toCoreModel(),
        type = type!!.toCoreModel(),
        statusPayload = statusPayload,
        statusUpdated = statusUpdated.toInstant(),
        updated = updated.toInstant(),
        thirdPartyAppAuthentication = null // TODO: rest setup
    )
}

fun CredentialsRestDTO.TypeEnum.toCoreModel() =
    when (this) {
        CredentialsRestDTO.TypeEnum.PASSWORD -> Credentials.Type.PASSWORD
        CredentialsRestDTO.TypeEnum.MOBILE_BANKID -> Credentials.Type.MOBILE_BANKID
        CredentialsRestDTO.TypeEnum.KEYFOB -> Credentials.Type.KEYFOB
        CredentialsRestDTO.TypeEnum.THIRD_PARTY_APP -> Credentials.Type.THIRD_PARTY_AUTHENTICATION
    }

fun CredentialsRestDTO.StatusEnum.toCoreModel() =
    when (this) {
        CredentialsRestDTO.StatusEnum.CREATED -> Credentials.Status.CREATED
        CredentialsRestDTO.StatusEnum.AUTHENTICATING -> Credentials.Status.AUTHENTICATING
        CredentialsRestDTO.StatusEnum.AWAITING_MOBILE_BANKID_AUTHENTICATION -> Credentials.Status.AWAITING_MOBILE_BANKID_AUTHENTICATION
        CredentialsRestDTO.StatusEnum.AWAITING_SUPPLEMENTAL_INFORMATION -> Credentials.Status.AWAITING_SUPPLEMENTAL_INFORMATION
        CredentialsRestDTO.StatusEnum.UPDATING -> Credentials.Status.UPDATING
        CredentialsRestDTO.StatusEnum.UPDATED -> Credentials.Status.UPDATED
        CredentialsRestDTO.StatusEnum.AUTHENTICATION_ERROR -> Credentials.Status.AUTHENTICATION_ERROR
        CredentialsRestDTO.StatusEnum.TEMPORARY_ERROR -> Credentials.Status.TEMPORARY_ERROR
        CredentialsRestDTO.StatusEnum.PERMANENT_ERROR -> Credentials.Status.PERMANENT_ERROR
        CredentialsRestDTO.StatusEnum.AWAITING_THIRD_PARTY_APP_AUTHENTICATION -> Credentials.Status.AWAITING_THIRD_PARTY_APP_AUTHENTICATION
        CredentialsRestDTO.StatusEnum.DELETED -> Credentials.Status.DELETED
        CredentialsRestDTO.StatusEnum.SESSION_EXPIRED -> Credentials.Status.SESSION_EXPIRED
    }
