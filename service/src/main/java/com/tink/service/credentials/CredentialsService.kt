package com.tink.service.credentials

import com.tink.model.credentials.Credentials
import com.tink.service.handler.ResultHandler
import com.tink.service.streaming.publisher.Stream

interface CredentialsService {
    fun list(): Stream<List<Credentials>>
    suspend fun create(descriptor: CredentialsCreationDescriptor): Credentials
    suspend fun delete(credentialsId: String)
    suspend fun update(descriptor: CredentialsUpdateDescriptor): Credentials
    fun refresh(credentialsIds: List<String>, handler: ResultHandler<Unit>)
    fun enable(credentialsId: String, handler: ResultHandler<Unit>)
    fun disable(credentialsId: String, handler: ResultHandler<Unit>)
    fun supplementInformation(credentialsId: String, information: Map<String, String>, handler: ResultHandler<Unit>)
    fun cancelSupplementalInformation(credentialsId: String, handler: ResultHandler<Unit>)
    fun thirdPartyCallback(state: String, parameters: Map<String, String>, handler: ResultHandler<Unit>)
}
