package com.tink.service.credentials

import com.tink.model.authentication.ThirdPartyAppAuthentication
import com.tink.model.credentials.Credentials
import com.tink.model.misc.Field
import com.tink.service.misc.toInstant
import java.util.regex.Pattern
import com.tink.service.generated.models.Credentials as CredentialsRestDTO
import com.tink.service.generated.models.Credentials.ThirdPartyAuthentication as ThirdPartyAuthRestDto
import com.tink.service.generated.models.Field as FieldRestDTO

fun CredentialsRestDTO.toCoreModel(): Credentials {

    val status = status!!.toCoreModel()

    val thirdPartyAuth: ThirdPartyAppAuthentication?
    val supplementalInfo: List<Field>
    when (status) {
        Credentials.Status.AWAITING_MOBILE_BANKID_AUTHENTICATION -> {
            thirdPartyAuth =
                createThirdPartyAuthFromAutostartToken(supplementalInformation?.rawStringInfo)
            supplementalInfo = listOf()
        }
        Credentials.Status.AWAITING_THIRD_PARTY_APP_AUTHENTICATION -> {
            thirdPartyAuth = supplementalInformation?.thirdPartyAuthentication?.toCoreModel()
            supplementalInfo = listOf()
        }
        Credentials.Status.AWAITING_SUPPLEMENTAL_INFORMATION -> {
            thirdPartyAuth = null
            supplementalInfo =
                supplementalInformation?.fieldList?.map { it.toCoreModel() } ?: listOf()
        }
        else -> {
            thirdPartyAuth = null
            supplementalInfo = listOf()
        }
    }

    return Credentials(
        id = id!!,
        providerName = providerName,
        fields = fields,
        supplementalInformation = supplementalInfo,
        sessionExpiryDate = sessionExpiryDate.toInstant(),
        status = status,
        type = type!!.toCoreModel(),
        statusPayload = statusPayload,
        statusUpdated = statusUpdated.toInstant(),
        updated = updated.toInstant(),
        thirdPartyAppAuthentication = thirdPartyAuth
    )
}

fun ThirdPartyAuthRestDto.toCoreModel() =
    ThirdPartyAppAuthentication(
        downloadMessage = downloadMessage ?: "",
        downloadTitle = downloadTitle ?: "",
        upgradeMessage = upgradeMessage ?: "",
        upgradeTitle = upgradeTitle ?: "",
        android = ThirdPartyAppAuthentication.Android(
            intent = android.intent,
            packageName = android.packageName ?: "",
            requiredMinimumVersion = android.requiredMinimumVersion
        )
    )

fun createThirdPartyAuthFromAutostartToken(autostartToken: String?): ThirdPartyAppAuthentication? {
    val validAutostartToken = autostartToken?.let {
        Pattern.compile("^[a-f0-9-]+\$").matcher(autostartToken).matches()
    }
    if (validAutostartToken == true) {
        // TODO: Localization
        return ThirdPartyAppAuthentication(
            downloadTitle = "Download Mobile BankID",
            downloadMessage = "You need to install the Mobile BankID app to authenticate",
            upgradeTitle = "",
            upgradeMessage = "",
            android = ThirdPartyAppAuthentication.Android(
                intent = "bankid:///?autostarttoken=$autostartToken",
                packageName = "com.bankid.bus",
                requiredMinimumVersion = 0
            )
        )
    }
    return null
}

// TODO: Move to more general file
fun FieldRestDTO.toCoreModel(): Field {
    return Field(
        name = name ?: "",
        value = value ?: "",
        validationRules = Field.ValidationRules(
            maxLength = maxLength ?: 0,
            minLength = minLength ?: 0,
            pattern = pattern ?: "",
            patternError = patternError ?: "",
            isOptional = optional ?: false
        ),
        attributes = Field.Attributes(
            description = description ?: "",
            hint = hint ?: "",
            helpText = helpText ?: "",
            inputType = Field.InputType(
                isMasked = masked ?: false,
                isNumeric = numeric ?: false,
                isImmutable = immutable ?: false
            )
        )
    )
}

fun CredentialsRestDTO.TypeEnum.toCoreModel() =
    when (this) {
        CredentialsRestDTO.TypeEnum.PASSWORD -> Credentials.Type.PASSWORD
        CredentialsRestDTO.TypeEnum.MOBILE_BANKID -> Credentials.Type.MOBILE_BANKID
        CredentialsRestDTO.TypeEnum.KEYFOB -> Credentials.Type.KEYFOB
        CredentialsRestDTO.TypeEnum.FRAUD -> Credentials.Type.FRAUD
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
