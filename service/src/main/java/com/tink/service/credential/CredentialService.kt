package com.tink.service.credential

import com.tink.model.credential.Credentials
import com.tink.service.handler.ResultHandler
import com.tink.service.streaming.publisher.Stream

interface CredentialService {
    fun list(): Stream<List<Credentials>>
    fun create(descriptor: CredentialCreationDescriptor, handler: ResultHandler<Credentials>)
    fun delete(credentialId: String, handler: ResultHandler<Unit>)
    fun update(descriptor: CredentialUpdateDescriptor, handler: ResultHandler<Credentials>)
    fun refresh(credentialIds: List<String>, handler: ResultHandler<Unit>)
    fun enable(credentialId: String, handler: ResultHandler<Unit>)
    fun disable(credentialId: String, handler: ResultHandler<Unit>)
    fun supplementInformation(credentialId: String, information: Map<String, String>, handler: ResultHandler<Unit>)
    fun cancelSupplementalInformation(credentialId: String, handler: ResultHandler<Unit>)
    fun thirdPartyCallback(state: String, parameters: Map<String, String>, handler: ResultHandler<Unit>)
}
