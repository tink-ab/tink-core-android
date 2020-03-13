package com.tink.service.credential

import com.tink.model.authentication.ThirdPartyAppAuthentication
import com.tink.model.credential.Credentials
import com.tink.service.misc.toField
import com.tink.service.misc.toInstant
import se.tink.grpc.v1.rpc.CreateCredentialRequest
import se.tink.grpc.v1.rpc.UpdateCredentialRequest

internal typealias CredentialsDTO = se.tink.grpc.v1.models.Credential
internal typealias CredentialsTypeDTO = se.tink.grpc.v1.models.Credential.Type
internal typealias CredentialsStatusDTO = se.tink.grpc.v1.models.Credential.Status
internal typealias ThirdPartyAppAuthenticationDTO = se.tink.grpc.v1.models.ThirdPartyAppAuthentication
internal typealias ThirdPartyAppAuthenticationAndroidDTO = se.tink.grpc.v1.models.ThirdPartyAppAuthentication.Android

internal fun CredentialsDTO.toCredential() =
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

internal fun CredentialsTypeDTO.toCredentialType() =
    when (this) {
        CredentialsTypeDTO.UNRECOGNIZED,
        CredentialsTypeDTO.TYPE_UNKNOWN -> Credentials.Type.UNKNOWN
        CredentialsTypeDTO.TYPE_PASSWORD -> Credentials.Type.PASSWORD
        CredentialsTypeDTO.TYPE_MOBILE_BANKID -> Credentials.Type.MOBILE_BANKID
        CredentialsTypeDTO.TYPE_KEYFOB -> Credentials.Type.KEYFOB
        CredentialsTypeDTO.TYPE_FRAUD -> Credentials.Type.FRAUD
        CredentialsTypeDTO.TYPE_THIRD_PARTY_AUTHENTICATION -> Credentials.Type.THIRD_PARTY_AUTHENTICATION
    }

internal fun CredentialsStatusDTO.toCredentialStatus() =
    when (this) {
        CredentialsStatusDTO.UNRECOGNIZED,
        CredentialsStatusDTO.STATUS_UNKNOWN -> Credentials.Status.UNKNOWN
        CredentialsStatusDTO.STATUS_CREATED -> Credentials.Status.CREATED
        CredentialsStatusDTO.STATUS_AUTHENTICATING -> Credentials.Status.AUTHENTICATING
        CredentialsStatusDTO.STATUS_UPDATING -> Credentials.Status.UPDATING
        CredentialsStatusDTO.STATUS_UPDATED -> Credentials.Status.UPDATED
        CredentialsStatusDTO.STATUS_TEMPORARY_ERROR -> Credentials.Status.TEMPORARY_ERROR
        CredentialsStatusDTO.STATUS_AUTHENTICATION_ERROR -> Credentials.Status.AUTHENTICATION_ERROR
        CredentialsStatusDTO.STATUS_PERMANENT_ERROR -> Credentials.Status.PERMANENT_ERROR
        CredentialsStatusDTO.STATUS_AWAITING_MOBILE_BANKID_AUTHENTICATION -> Credentials.Status.AWAITING_MOBILE_BANKID_AUTHENTICATION
        CredentialsStatusDTO.STATUS_AWAITING_SUPPLEMENTAL_INFORMATION -> Credentials.Status.AWAITING_SUPPLEMENTAL_INFORMATION
        CredentialsStatusDTO.STATUS_DISABLED -> Credentials.Status.DISABLED
        CredentialsStatusDTO.STATUS_AWAITING_THIRD_PARTY_APP_AUTHENTICATION -> Credentials.Status.AWAITING_THIRD_PARTY_APP_AUTHENTICATION
        CredentialsStatusDTO.STATUS_SESSION_EXPIRED -> Credentials.Status.SESSION_EXPIRED
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

internal fun CredentialsCreationDescriptor.toRequest() =
    CreateCredentialRequest
        .newBuilder()
        .setProviderName(providerName)
        .setType(type.toDTO())
        .putAllFields(fields)
        .setAppUri(appUri.toString())
        .build()

internal fun CredentialsUpdateDescriptor.toRequest() =
    UpdateCredentialRequest
        .newBuilder()
        .setCredentialId(id)
        .putAllFields(fields)
        .setAppUri(appUri.toString())
        .build()

internal fun Credentials.Type.toDTO() =
    when (this) {
        Credentials.Type.UNKNOWN -> CredentialsTypeDTO.TYPE_UNKNOWN
        Credentials.Type.PASSWORD -> CredentialsTypeDTO.TYPE_PASSWORD
        Credentials.Type.MOBILE_BANKID -> CredentialsTypeDTO.TYPE_MOBILE_BANKID
        Credentials.Type.KEYFOB -> CredentialsTypeDTO.TYPE_KEYFOB
        Credentials.Type.FRAUD -> CredentialsTypeDTO.TYPE_FRAUD
        Credentials.Type.THIRD_PARTY_AUTHENTICATION -> CredentialsTypeDTO.TYPE_THIRD_PARTY_AUTHENTICATION
    }