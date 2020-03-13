package com.tink.service.credential

import com.tink.model.authentication.ThirdPartyAppAuthentication
import com.tink.model.credential.Credentials
import com.tink.service.misc.toField
import com.tink.service.misc.toInstant
import se.tink.grpc.v1.rpc.CreateCredentialRequest
import se.tink.grpc.v1.rpc.UpdateCredentialRequest

internal typealias CredentialDTO = se.tink.grpc.v1.models.Credential
internal typealias CredentialTypeDTO = se.tink.grpc.v1.models.Credential.Type
internal typealias CredentialStatusDTO = se.tink.grpc.v1.models.Credential.Status
internal typealias ThirdPartyAppAuthenticationDTO = se.tink.grpc.v1.models.ThirdPartyAppAuthentication
internal typealias ThirdPartyAppAuthenticationAndroidDTO = se.tink.grpc.v1.models.ThirdPartyAppAuthentication.Android

internal fun CredentialDTO.toCredential() =
    Credentials(
        providerName = providerName,
        type = type.toCredentialType(),
        id = id,
        updated = updated.toInstant(),
        statusUpdated = statusUpdated.toInstant(),
        status = status.toCredentialStatus(),
        statusPayload = statusPayload,
        fields = fieldsMap,
        supplementalInformation = supplementalInformationFieldsList.map { it.toField() },
        sessionExpiryDate = sessionExpiryDate
            .takeIf { hasSessionExpiryDate() }
            ?.toInstant(),
        thirdPartyAppAuthentication = thirdPartyAppAuthentication
            .takeIf { hasThirdPartyAppAuthentication() }
            ?.toThirdPartyAppAuthentication()
    )

internal fun CredentialTypeDTO.toCredentialType() =
    when (this) {
        CredentialTypeDTO.UNRECOGNIZED,
        CredentialTypeDTO.TYPE_UNKNOWN -> Credentials.Type.UNKNOWN
        CredentialTypeDTO.TYPE_PASSWORD -> Credentials.Type.PASSWORD
        CredentialTypeDTO.TYPE_MOBILE_BANKID -> Credentials.Type.MOBILE_BANKID
        CredentialTypeDTO.TYPE_KEYFOB -> Credentials.Type.KEYFOB
        CredentialTypeDTO.TYPE_FRAUD -> Credentials.Type.FRAUD
        CredentialTypeDTO.TYPE_THIRD_PARTY_AUTHENTICATION -> Credentials.Type.THIRD_PARTY_AUTHENTICATION
    }

internal fun CredentialStatusDTO.toCredentialStatus() =
    when (this) {
        CredentialStatusDTO.UNRECOGNIZED,
        CredentialStatusDTO.STATUS_UNKNOWN -> Credentials.Status.UNKNOWN
        CredentialStatusDTO.STATUS_CREATED -> Credentials.Status.CREATED
        CredentialStatusDTO.STATUS_AUTHENTICATING -> Credentials.Status.AUTHENTICATING
        CredentialStatusDTO.STATUS_UPDATING -> Credentials.Status.UPDATING
        CredentialStatusDTO.STATUS_UPDATED -> Credentials.Status.UPDATED
        CredentialStatusDTO.STATUS_TEMPORARY_ERROR -> Credentials.Status.TEMPORARY_ERROR
        CredentialStatusDTO.STATUS_AUTHENTICATION_ERROR -> Credentials.Status.AUTHENTICATION_ERROR
        CredentialStatusDTO.STATUS_PERMANENT_ERROR -> Credentials.Status.PERMANENT_ERROR
        CredentialStatusDTO.STATUS_AWAITING_MOBILE_BANKID_AUTHENTICATION -> Credentials.Status.AWAITING_MOBILE_BANKID_AUTHENTICATION
        CredentialStatusDTO.STATUS_AWAITING_SUPPLEMENTAL_INFORMATION -> Credentials.Status.AWAITING_SUPPLEMENTAL_INFORMATION
        CredentialStatusDTO.STATUS_DISABLED -> Credentials.Status.DISABLED
        CredentialStatusDTO.STATUS_AWAITING_THIRD_PARTY_APP_AUTHENTICATION -> Credentials.Status.AWAITING_THIRD_PARTY_APP_AUTHENTICATION
        CredentialStatusDTO.STATUS_SESSION_EXPIRED -> Credentials.Status.SESSION_EXPIRED
    }

internal fun ThirdPartyAppAuthenticationDTO.toThirdPartyAppAuthentication() =
    ThirdPartyAppAuthentication(
        downloadTitle = downloadTitle,
        downloadMessage = downloadMessage,
        upgradeTitle = upgradeTitle,
        upgradeMessage = upgradeMessage,
        android = android.toThirdPartyAndroid()
    )

internal fun ThirdPartyAppAuthenticationAndroidDTO.toThirdPartyAndroid() =
    ThirdPartyAppAuthentication.Android(
        intent = intent,
        packageName = packageName,
        requiredMinimumVersion = requiredMinimumVersion
    )

internal fun CredentialCreationDescriptor.toRequest() =
    CreateCredentialRequest
        .newBuilder()
        .setProviderName(providerName)
        .setType(type.toDTO())
        .putAllFields(fields)
        .setAppUri(appUri.toString())
        .build()

internal fun CredentialUpdateDescriptor.toRequest() =
    UpdateCredentialRequest
        .newBuilder()
        .setCredentialId(id)
        .putAllFields(fields)
        .setAppUri(appUri.toString())
        .build()

internal fun Credentials.Type.toDTO() =
    when (this) {
        Credentials.Type.UNKNOWN -> CredentialTypeDTO.TYPE_UNKNOWN
        Credentials.Type.PASSWORD -> CredentialTypeDTO.TYPE_PASSWORD
        Credentials.Type.MOBILE_BANKID -> CredentialTypeDTO.TYPE_MOBILE_BANKID
        Credentials.Type.KEYFOB -> CredentialTypeDTO.TYPE_KEYFOB
        Credentials.Type.FRAUD -> CredentialTypeDTO.TYPE_FRAUD
        Credentials.Type.THIRD_PARTY_AUTHENTICATION -> CredentialTypeDTO.TYPE_THIRD_PARTY_AUTHENTICATION
    }