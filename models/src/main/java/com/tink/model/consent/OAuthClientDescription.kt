package com.tink.model.consent

import com.tink.model.user.Scope

data class OAuthClientDescription(
    val clientName: String,
    val clientUrl: String,
    val clientIconUrl: String,
    val embeddedAllowed: Boolean,
    val verified: Boolean,
    val aggregator: Boolean,
    val scopeDescriptions: List<ScopeDescription>
)

/**
 * A title and description explaining a specific [Scope].
 */
data class ScopeDescription(
    /** The short string of what access the scope is for. */
    val title: String,
    /** A string describing what user data the scope provides access to. */
    val description: String
)
