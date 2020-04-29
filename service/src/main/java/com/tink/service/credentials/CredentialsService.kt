package com.tink.service.credentials

import com.tink.model.credentials.Credentials
import com.tink.service.streaming.publisher.Stream

interface CredentialsService {
    fun list(): Stream<List<Credentials>>
    suspend fun create(descriptor: CredentialsCreationDescriptor): Credentials
    suspend fun delete(credentialsId: String)
    suspend fun update(descriptor: CredentialsUpdateDescriptor): Credentials
    suspend fun refresh(descriptor: CredentialsRefreshDescriptor)
    suspend fun authenticate(descriptor: CredentialsAuthenticateDescriptor)
    suspend fun enable(credentialsId: String)
    suspend fun disable(credentialsId: String)
    suspend fun supplementInformation(credentialsId: String, information: Map<String, String>)
    suspend fun cancelSupplementalInformation(credentialsId: String)
    suspend fun thirdPartyCallback(state: String, parameters: Map<String, String>)
}
