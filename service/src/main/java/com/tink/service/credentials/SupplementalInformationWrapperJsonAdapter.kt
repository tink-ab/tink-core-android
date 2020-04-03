package com.tink.service.credentials

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import com.squareup.moshi.Types
import com.tink.service.generated.models.Credentials
import com.tink.service.generated.models.Field
import okio.buffer
import okio.source
import java.io.ByteArrayInputStream

class SupplementalInformationWrapperJsonAdapter(
    moshi: Moshi
) : JsonAdapter<Credentials.SupplementalInfoWrapper?>() {

    private val listOfFieldAdapter: JsonAdapter<List<Field>> =
        moshi.adapter(
            Types.newParameterizedType(List::class.java, Field::class.java), emptySet()
        )

    private val thirdPartyAuthenticationJsonAdapter: JsonAdapter<Credentials.ThirdPartyAuthentication> =
        moshi.adapter(Credentials.ThirdPartyAuthentication::class.java)

    @FromJson
    override fun fromJson(reader: JsonReader): Credentials.SupplementalInfoWrapper? {

        if (reader.peek() == JsonReader.Token.STRING) {

            val supplementalInfoString = reader.nextString()

            val newReader = JsonReader.of(
                ByteArrayInputStream(supplementalInfoString.toByteArray()).source().buffer()
            )

            return when (newReader.peek()) {
                JsonReader.Token.BEGIN_OBJECT -> {
                    val thirdParty = thirdPartyAuthenticationJsonAdapter.fromJson(newReader)
                    Credentials.SupplementalInfoWrapper(thirdPartyAuthentication = thirdParty)
                }
                JsonReader.Token.BEGIN_ARRAY -> {
                    val fieldList = listOfFieldAdapter.fromJson(newReader)
                    Credentials.SupplementalInfoWrapper(fieldList = fieldList)
                }
                JsonReader.Token.STRING -> {
                    Credentials.SupplementalInfoWrapper(unknownValue = newReader.nextString())
                }
                else -> null
            }
        }
        return null
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: Credentials.SupplementalInfoWrapper?) {
        TODO("Not yet implemented")
    }
}
