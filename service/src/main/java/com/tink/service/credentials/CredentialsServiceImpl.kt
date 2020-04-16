package com.tink.service.credentials

import com.tink.model.credentials.Credentials
import com.tink.service.di.ServiceScope
import com.tink.service.generated.apis.CredentialsApi
import com.tink.service.generated.models.CallbackRelayedRequest
import com.tink.service.generated.models.CreateCredentialsRequest
import com.tink.service.generated.models.RefreshCredentialsRequest
import com.tink.service.generated.models.SupplementalInformation
import com.tink.service.generated.models.UpdateCredentialsRequest
import com.tink.service.generated.tools.unwrap
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
            items = listOf()
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

    // TODO: Refreshable items
    override suspend fun refresh(credentialsId: String) =
        api.refresh(credentialsId, RefreshCredentialsRequest(), items = null, optIn = null).unwrap()

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
}
