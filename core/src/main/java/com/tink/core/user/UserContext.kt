package com.tink.core.user

import android.net.Uri
import com.tink.core.credentials.CredentialRepository
import com.tink.core.provider.ProviderRepository
import com.tink.service.authorization.Scope
import com.tink.service.handler.ResultHandler

interface UserContext {
    val providerRepository: ProviderRepository
    val credentialRepository: CredentialRepository
    fun handleUri(uri: Uri, resultHandler: ResultHandler<Unit>? = null): Boolean
    fun authorize(scopes: Set<Scope>, resultHandler: ResultHandler<String>)
}