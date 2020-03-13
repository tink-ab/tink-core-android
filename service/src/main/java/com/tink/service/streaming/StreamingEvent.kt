package com.tink.service.streaming

import com.tink.model.account.Account
import com.tink.model.credential.Credentials
import com.tink.model.provider.Provider

sealed class StreamingEvent {

    abstract val type: Type

    enum class Type {
        CREATE,
        READ,
        UPDATE,
        DELETE,
        UNKNOWN
    }

    data class ProviderEvent(
        val providers: List<Provider>,
        override val type: Type
    ) : StreamingEvent()

    data class AccountEvent(
        val accounts: List<Account>,
        override val type: Type
    ) : StreamingEvent()

    data class CredentialEvent(
        val credentials: List<Credentials>,
        override val type: Type
    ) : StreamingEvent()
}