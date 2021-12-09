package com.tink.service.account

import com.tink.model.account.Account
import com.tink.model.misc.ExactNumber

data class UpdateAccountDescriptor(
    val id: String,
    val accountExclusion: Boolean? = null,
    val favored: Boolean? = null,
    val name: String? = null,
    val ownership: ExactNumber? = null,
    val type: Account.Type? = null
)
