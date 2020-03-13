package com.tink.service.credential

import android.net.Uri
import com.tink.model.credential.Credentials

data class CredentialsCreationDescriptor(
    val providerName: String,
    val type: Credentials.Type,
    val fields: Map<String, String>,
    val appUri: Uri
)

data class CredentialsUpdateDescriptor(
    val id: String,
    val fields: Map<String, String>,
    val appUri: Uri
)
