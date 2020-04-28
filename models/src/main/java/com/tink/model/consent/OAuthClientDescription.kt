package com.tink.model.consent

import android.os.Parcelable
import com.tink.model.user.Scope
import kotlinx.android.parcel.Parcelize

/**
 * A description of the OAuth client and the data it will collect from the user.
 *
 * @param clientName: The name of the application specified by the client when creating it in the console.
 * @param clientUrl: A url specified by the client for additional information. Can be empty.
 * @param clientIconUrl: A url for a client icon. Can be empty.
 * @param scopeDescriptions: A list of [ScopeDescription]s describing which financial info is going to be collected.
 * @param verified: Indicates whether the app is verified or not. If it is unverified, the user should be cautious when sharing financial data.
 * @param aggregator: Describes whether the client is aggregating data under their own or under Tink's license.
 */
@Parcelize
data class OAuthClientDescription(
    val clientName: String,
    val clientUrl: String,
    val clientIconUrl: String,
    val verified: Boolean,
    val aggregator: Boolean,
    val scopeDescriptions: List<ScopeDescription>
) : Parcelable

/**
 * A title and description explaining a specific [Scope].
 */
@Parcelize
data class ScopeDescription(
    /** The short string of what access the scope is for. */
    val title: String,
    /** A string describing what user data the scope provides access to. */
    val description: String
) : Parcelable
