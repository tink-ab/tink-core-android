package com.tink.rest.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Beneficiary(
    @field:Json(name = "ownerAccountId") val ownerAccountId: String,
    @field:Json(name = "accountNumber") val accountNumber: String,
    @field:Json(name = "accountNumberType") val accountNumberType: String,
    @field:Json(name = "name") val name: String
)

@JsonClass(generateAdapter = true)
data class BeneficiaryResponse(
    @field:Json(name = "beneficiaries") val beneficiaries: List<Beneficiary>
)