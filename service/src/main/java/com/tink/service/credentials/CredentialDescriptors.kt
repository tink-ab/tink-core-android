package com.tink.service.credentials

import android.net.Uri
import com.tink.model.credentials.Credentials
import com.tink.model.credentials.RefreshableItem

data class CredentialsCreationDescriptor(
    val providerName: String,
    val type: Credentials.Type,
    val fields: Map<String, String>,
    val appUri: Uri,
    val refreshableItems: Collection<RefreshableItem>? = null
)

data class CredentialsUpdateDescriptor(
    val id: String,
    val providerName: String,
    val fields: Map<String, String>,
    val appUri: Uri
)

data class CredentialsRefreshDescriptor(
    val id: String,
    val refreshableItems: Collection<RefreshableItem>? = null,
    val authenticate: Boolean? = null
)

data class CredentialsAuthenticateDescriptor(
    val id: String,
    val appUri: Uri
)
