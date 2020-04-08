package com.tink.service.credentials

import android.net.Uri
import com.tink.model.credentials.Credentials

data class CredentialsCreationDescriptor(
    val providerName: String,
    val type: Credentials.Type,
    val fields: Map<String, String>,
    val appUri: Uri
)

data class CredentialsUpdateDescriptor(
    val id: String,
    val providerName: String,
    val fields: Map<String, String>,
    val appUri: Uri
)
