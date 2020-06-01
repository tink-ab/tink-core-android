package com.tink.service.transfer

import com.tink.model.transfer.SignableOperation
import com.tink.rest.tools.GeneratedCodeConverters
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.threeten.bp.Instant
import com.tink.rest.models.SignableOperation as SignableOperationDto

internal class TransferConvertersTest {

    private val signableOperationExampleJson = "{\n" +
            "  \"created\": 1471349422000,\n" +
            "  \"credentialsId\": \"342220f1e0484c0481b2b468d7fbcfc4\",\n" +
            "  \"id\": \"a4516bda6ff545e0aa24e54b859579e0\",\n" +
            "  \"status\": \"EXECUTED\",\n" +
            "  \"statusMessage\": \"The transfer has been sent to your bank.\",\n" +
            "  \"type\": \"TRANSFER\",\n" +
            "  \"underlyingId\": \"1e09bab571d84b1cbe8d49c0be9c030f\",\n" +
            "  \"updated\": 1471349422000,\n" +
            "  \"userId\": \"2f37e3ff1e5342b39c41bee3ee73cf8e\"\n" +
            "}"

    @Test
    fun `basic SignableOperation converter test`() {

        val signableOperationDto =
            GeneratedCodeConverters.moshi
                .adapter(SignableOperationDto::class.java)
                .fromJson(signableOperationExampleJson)

        val signableOperation = signableOperationDto!!.toCoreModel()

        assertThat(signableOperation.id).isEqualTo("a4516bda6ff545e0aa24e54b859579e0")
        assertThat(signableOperation.credentialsId).isEqualTo("342220f1e0484c0481b2b468d7fbcfc4")
        assertThat(signableOperation.underlyingId).isEqualTo("1e09bab571d84b1cbe8d49c0be9c030f")
        assertThat(signableOperation.userId).isEqualTo("2f37e3ff1e5342b39c41bee3ee73cf8e")
        assertThat(signableOperation.status).isEqualTo(SignableOperation.Status.EXECUTED)
        assertThat(signableOperation.statusMessage).isEqualTo("The transfer has been sent to your bank.")
        assertThat(signableOperation.type).isEqualTo(SignableOperation.Type.TRANSFER)
        assertThat(signableOperation.created).isEqualTo(Instant.ofEpochMilli(1471349422000))
        assertThat(signableOperation.updated).isEqualTo(Instant.ofEpochMilli(1471349422000))
    }
}
