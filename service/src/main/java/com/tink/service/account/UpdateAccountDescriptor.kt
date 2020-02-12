package com.tink.service.account

import com.tink.model.account.Account
import com.tink.model.misc.ExactNumber

data class UpdateAccountDescriptor(
    val id: String,
    val excluded: Boolean,
    val favored: Boolean,
    val name: String,
    val ownership: ExactNumber,
    val type: Account.Type
)