package com.tink.service.credentials

import com.tink.model.credentials.Credentials
import com.tink.service.di.ServiceScope
import com.tink.service.handler.ResultHandler
import com.tink.service.handler.toStreamObserver
import com.tink.service.streaming.PollingHandler
import com.tink.service.streaming.publisher.Stream
import com.tink.service.streaming.publisher.toGrpcStreamObserver
import io.grpc.Channel
import se.tink.grpc.v1.rpc.CancelSupplementInformationRequest
import se.tink.grpc.v1.rpc.DeleteCredentialRequest
import se.tink.grpc.v1.rpc.DisableCredentialRequest
import se.tink.grpc.v1.rpc.EnableCredentialRequest
import se.tink.grpc.v1.rpc.ListCredentialsRequest
import se.tink.grpc.v1.rpc.RefreshCredentialsRequest
import se.tink.grpc.v1.rpc.SupplementInformationRequest
import se.tink.grpc.v1.rpc.ThirdPartyCallbackRequest
import se.tink.grpc.v1.services.CredentialServiceGrpc
import javax.inject.Inject

@ServiceScope
class CredentialsServiceImpl @Inject constructor(
    channel: Channel
) : CredentialsService {

    private val stub = CredentialServiceGrpc.newStub(channel)

    override fun list(): Stream<List<Credentials>> {
        return PollingHandler { observer ->
                stub.listCredentials(
                    ListCredentialsRequest.getDefaultInstance(),
                    observer.toGrpcStreamObserver { value ->
                        value.credentialsList.map { it.toCredentials() }
                    })
            }
    }

    override fun create(
        descriptor: CredentialsCreationDescriptor,
        handler: ResultHandler<Credentials>
    ) = stub.createCredential(descriptor.toRequest(), handler.toStreamObserver {
        it.credential.toCredentials()
    })

    override fun delete(credentialsId: String, handler: ResultHandler<Unit>) =
        DeleteCredentialRequest
            .newBuilder()
            .setCredentialId(credentialsId)
            .build()
            .let {
                stub.deleteCredential(it, handler.toStreamObserver())
            }

    override fun update(
        descriptor: CredentialsUpdateDescriptor,
        handler: ResultHandler<Credentials>
    ) = stub.updateCredential(descriptor.toRequest(), handler.toStreamObserver { response ->
        response.credential.toCredentials()
    })

    override fun refresh(credentialsIds: List<String>, handler: ResultHandler<Unit>) =
        RefreshCredentialsRequest
            .newBuilder()
            .addAllCredentialIds(credentialsIds)
            .build()
            .let {
                stub.refreshCredentials(it, handler.toStreamObserver())
            }

    override fun enable(credentialsId: String, handler: ResultHandler<Unit>) =
        EnableCredentialRequest
            .newBuilder()
            .setCredentialId(credentialsId)
            .build()
            .let {
                stub.enableCredential(it, handler.toStreamObserver())
            }

    override fun disable(credentialsId: String, handler: ResultHandler<Unit>) =
        DisableCredentialRequest
            .newBuilder()
            .setCredentialId(credentialsId)
            .build()
            .let {
                stub.disableCredential(it, handler.toStreamObserver())
            }

    override fun supplementInformation(
        credentialsId: String,
        information: Map<String, String>,
        handler: ResultHandler<Unit>
    ) =
        SupplementInformationRequest
            .newBuilder()
            .setCredentialId(credentialsId)
            .putAllSupplementalInformationFields(information)
            .build()
            .let {
                stub.supplementInformation(it, handler.toStreamObserver())
            }

    override fun cancelSupplementalInformation(credentialsId: String, handler: ResultHandler<Unit>) =
        CancelSupplementInformationRequest
            .newBuilder()
            .setCredentialId(credentialsId)
            .build()
            .let {
                stub.cancelSupplementInformation(it, handler.toStreamObserver())
            }

    override fun thirdPartyCallback(state: String, parameters: Map<String, String>, handler: ResultHandler<Unit>) =
        ThirdPartyCallbackRequest
            .newBuilder()
            .setState(state)
            .putAllParameters(parameters)
            .build()
            .let {
                stub.thirdPartyCallback(it, handler.toStreamObserver())
            }
}
