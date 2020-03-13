package com.tink.service.credentials

import com.tink.model.credentials.Credentials
import com.tink.service.handler.ResultHandler
import com.tink.service.streaming.publisher.Stream

interface CredentialsService {
    fun list(): Stream<List<Credentials>>
    fun create(descriptor: CredentialsCreationDescriptor, handler: ResultHandler<Credentials>)
    fun delete(credentialId: String, handler: ResultHandler<Unit>)
    fun update(descriptor: CredentialsUpdateDescriptor, handler: ResultHandler<Credentials>)
    fun refresh(credentialIds: List<String>, handler: ResultHandler<Unit>)
    fun enable(credentialId: String, handler: ResultHandler<Unit>)
    fun disable(credentialId: String, handler: ResultHandler<Unit>)
    fun supplementInformation(credentialId: String, information: Map<String, String>, handler: ResultHandler<Unit>)
    fun cancelSupplementalInformation(credentialId: String, handler: ResultHandler<Unit>)
    fun thirdPartyCallback(state: String, parameters: Map<String, String>, handler: ResultHandler<Unit>)
}
