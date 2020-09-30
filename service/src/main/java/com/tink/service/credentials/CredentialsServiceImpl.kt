package com.tink.service.credentials

import com.tink.model.credentials.Credentials
import com.tink.service.di.ServiceScope
import com.tink.rest.apis.CredentialsApi
import com.tink.rest.models.CallbackRelayedRequest
import com.tink.rest.models.CreateCredentialsRequest
import com.tink.rest.models.ManualAuthenticationRequest
import com.tink.rest.models.RefreshCredentialsRequest
import com.tink.rest.models.SupplementalInformation
import com.tink.rest.models.UpdateCredentialsRequest
import com.tink.rest.tools.unwrap
import com.tink.service.streaming.PollingHandler
import com.tink.service.streaming.publisher.Stream
import javax.inject.Inject

@ServiceScope
internal class CredentialsServiceImpl @Inject constructor(
    private val api: CredentialsApi
) : CredentialsService {

    override fun list(): Stream<List<Credentials>> {
        return PollingHandler { observer ->
            try {
                val response = api.getCredentialsList()
                val credentials = response.credentials?.map { it.toCoreModel() } ?: listOf()
                observer.onNext(credentials)
            } catch (exception: Exception) {
                observer.onError(exception)
            }
        }
    }

    override suspend fun create(descriptor: CredentialsCreationDescriptor) =
        api.create(
            CreateCredentialsRequest(
                providerName = descriptor.providerName,
                fields = descriptor.fields,
                appUri = descriptor.appUri.toString()
            ),
            items = descriptor.refreshableItems?.map { it.item }
        ).toCoreModel()

    override suspend fun delete(credentialsId: String) = api.delete(credentialsId).unwrap()

    override suspend fun update(descriptor: CredentialsUpdateDescriptor) =
        api.update(
            descriptor.id,
            UpdateCredentialsRequest(
                providerName = descriptor.providerName,
                fields = descriptor.fields,
                appUri = descriptor.appUri.toString()
            )
        ).toCoreModel()

    override suspend fun refresh(descriptor: CredentialsRefreshDescriptor) =
        api.refresh(
            descriptor.id,
            RefreshCredentialsRequest(),
            items = descriptor.refreshableItems?.map { it.item },
            authenticate = descriptor.authenticate,
            optIn = null
        ).unwrap()

    override suspend fun authenticate(descriptor: CredentialsAuthenticateDescriptor) =
        api.manualAuthentication(
            descriptor.id, ManualAuthenticationRequest(
                appUri = descriptor.appUri.toString()
            )
        ).unwrap()

    override suspend fun disable(credentialsId: String) = api.enable(credentialsId).unwrap()

    override suspend fun enable(credentialsId: String) = api.enable(credentialsId).unwrap()

    override suspend fun supplementInformation(
        credentialsId: String,
        information: Map<String, String>
    ) = api.supplemental(credentialsId, SupplementalInformation(information)).unwrap()

    // Uses workaround since the endpoint is not exposed in REST
    override suspend fun cancelSupplementalInformation(credentialsId: String) =
        supplementInformation(credentialsId, mapOf())

    override suspend fun thirdPartyCallback(
        state: String,
        parameters: Map<String, String>
    ) = api.thirdPartyCallbackRelayedPost(CallbackRelayedRequest(state, parameters)).unwrap()

    override suspend fun getCredentials(id: String): Credentials =
        api.get(id).toCoreModel()
}
