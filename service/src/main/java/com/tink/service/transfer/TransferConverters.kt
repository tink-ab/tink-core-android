package com.tink.service.transfer

import com.tink.model.transfer.Beneficiary
import com.tink.model.transfer.SignableOperation
import com.tink.rest.models.CreateBeneficiaryRequest
import com.tink.service.misc.toInstant
import com.tink.rest.models.Beneficiary as BeneficiaryDto
import com.tink.rest.models.SignableOperation as SignableOperationDto
import com.tink.rest.models.SignableOperation.StatusEnum as SignableOperationStatusDto
import com.tink.rest.models.SignableOperation.TypeEnum as SignableOperationTypeDto

internal fun SignableOperationDto.toCoreModel(): SignableOperation =
    SignableOperation(
        id = id!!,
        credentialsId = credentialsId,
        created = created.toInstant(),
        status = status!!.toCoreModel(),
        statusMessage = statusMessage ?: "",
        type = type!!.toCoreModel(),
        underlyingId = underlyingId!!,
        updated = updated.toInstant(),
        userId = userId!!
    )

internal fun SignableOperationTypeDto.toCoreModel(): SignableOperation.Type =
    when (this) {
        SignableOperationTypeDto.TRANSFER -> SignableOperation.Type.TRANSFER
        SignableOperationTypeDto.UNKNOWN -> SignableOperation.Type.UNKNOWN
    }

internal fun SignableOperationStatusDto.toCoreModel(): SignableOperation.Status =
    when (this) {
        SignableOperationStatusDto.CREATED -> SignableOperation.Status.CREATED
        SignableOperationStatusDto.EXECUTING -> SignableOperation.Status.EXECUTING
        SignableOperationStatusDto.AWAITING_CREDENTIALS -> SignableOperation.Status.AWAITING_CREDENTIALS
        SignableOperationStatusDto.CANCELLED -> SignableOperation.Status.CANCELLED
        SignableOperationStatusDto.FAILED -> SignableOperation.Status.FAILED
        SignableOperationStatusDto.EXECUTED -> SignableOperation.Status.EXECUTED
        SignableOperationStatusDto.AWAITING_THIRD_PARTY_APP_AUTHENTICATION ->
            SignableOperation.Status.AWAITING_THIRD_PARTY_APP_AUTHENTICATION
        SignableOperationStatusDto.UNKNOWN -> SignableOperation.Status.UNKNOWN
    }

internal fun BeneficiaryDto.toCoreModel() = Beneficiary(
    ownerAccountId = ownerAccountId,
    accountNumber = accountNumber,
    accountNumberType = accountNumberType,
    name = name
)

internal fun CreateBeneficiaryDescriptor.toRequest() =
    CreateBeneficiaryRequest(
        ownerAccountId = ownerAccountId,
        credentialsId = credentialsId,
        accountNumber = accountNumber,
        accountNumberType = accountNumberType,
        name = name
    )
