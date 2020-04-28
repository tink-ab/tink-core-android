package com.tink.model.user

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class Scope(private val scope: String): Parcelable {

    @Parcelize
    object TransactionsRead : Scope("transactions:read")

    @Parcelize
    object AccountsRead : Scope("accounts:read")

    @Parcelize
    object UserRead : Scope("user:read")

    @Parcelize
    object CredentialsRead : Scope("credentials:read")

    @Parcelize
    object IdentityRead : Scope("identity:read")

    @Parcelize
    object InvestmentsRead : Scope("investments:read")

    @Parcelize
    object StatisticsRead : Scope("statistics:read")

    @Parcelize
    class CustomScope(private val scope: String) : Scope(scope)

    override fun toString(): String = scope
}