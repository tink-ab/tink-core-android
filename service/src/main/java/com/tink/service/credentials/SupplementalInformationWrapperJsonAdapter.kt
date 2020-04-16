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

/**
 * Json adapter to convert the [Credentials.supplementalInformation] field.
 * This field will always contain a Json-string, which itself represents one of these three values:
 * - a list of [Field]s meant to be filled in by the user as additional information
 * - an object mapping to [Credentials.ThirdPartyAuthentication]
 * - a string representing an "autostartToken" to be used for authentication with BankId
 */
internal class SupplementalInformationWrapperJsonAdapter(
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

        // Make sure we are looking at a string (should always be the case)
        if (reader.peek() == JsonReader.Token.STRING) {

            val supplementalInfoString =
                reader.nextString() // raw json string representing the supplemental info

            // We need to create a new reader from this string so moshi can actually read it as json.
            // In simple terms, this will basically "remove the quotes" from the string
            val newReader = JsonReader
                .of(ByteArrayInputStream(supplementalInfoString.toByteArray()).source().buffer())
                .apply {
                    isLenient = true // make sure we don't crash if we have an unquoted string
                }

            return when (newReader.peek()) {
                JsonReader.Token.BEGIN_OBJECT -> { // We are looking at a Json-object, convert to third party object
                    val thirdParty = thirdPartyAuthenticationJsonAdapter.fromJson(newReader)
                    Credentials.SupplementalInfoWrapper(thirdPartyAuthentication = thirdParty)
                }
                JsonReader.Token.BEGIN_ARRAY -> { // We are looking at an array, convert to list of Fields
                    val fieldList = listOfFieldAdapter.fromJson(newReader)
                    Credentials.SupplementalInfoWrapper(fieldList = fieldList)
                }
                JsonReader.Token.STRING -> { // We are looking at a string, pass it raw and let the converter decide if it's valid
                    Credentials.SupplementalInfoWrapper(rawStringInfo = newReader.nextString())
                }
                else -> null
            }
        }
        return null
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: Credentials.SupplementalInfoWrapper?) {
        throw NotImplementedError("This operation is not implemented since it is not supposed to be used")
    }
}
