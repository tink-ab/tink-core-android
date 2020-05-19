package com.tink.rest.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Beneficiary(
    @field:Json(name = "accountId") val accountId: String?,
    @field:Json(name = "accountNumber") val accountNumber: String?,
    @field:Json(name = "name") val name: String?,
    @field:Json(name = "type") val type: String?
)

@JsonClass(generateAdapter = true)
data class BeneficiaryResponse(
    @field:Json(name = "beneficiaries") val beneficiaries: List<Beneficiary>
)