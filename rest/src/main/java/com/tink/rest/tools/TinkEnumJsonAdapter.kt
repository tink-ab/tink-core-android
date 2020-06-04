/*
* Copyright (C) 2018 Square, Inc.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.tink.rest.tools

import com.squareup.moshi.Json
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import java.io.IOException

/**
 * A JsonAdapter for enums that allows having a fallback enum value when a deserialized string does
 * not match any enum value. To use, add this as an adapter for your enum type on your [ ]:
 *
 * <pre> `Moshi moshi = new Moshi.Builder()
 * .add(CurrencyCode.class, EnumJsonAdapter.create(CurrencyCode.class)
 * .withUnknownFallback(CurrencyCode.USD))
 * .build();
`</pre> *
 */
class TinkEnumJsonAdapter<T : Enum<*>> private constructor(
    private val enumType: Class<T>,
    private val fallbackValue: T?,
    private val useFallbackValue: Boolean
) :
    JsonAdapter<T?>() {
    private val nameStrings: Array<String?>
    private val constants: Array<T>
    var options: JsonReader.Options? = null

    /**
     * Create a new adapter for this enum with a fallback value to use when the JSON string does not
     * match any of the enum's constants. Note that this value will not be used when the JSON value is
     * null, absent, or not a string. Also, the string values are case-sensitive, and this fallback
     * value will be used even on case mismatches.
     */
    fun withUnknownFallback(fallbackValue: T): TinkEnumJsonAdapter<T> {
        return TinkEnumJsonAdapter(enumType, fallbackValue, true)
    }

    @Throws(IOException::class)
    override fun fromJson(reader: JsonReader): T? {
        val index = reader.selectString(options!!)
        if (index != -1) return constants[index]
        val path = reader.path

        val unknownIndex: Int = nameStrings.indexOf("UNKNOWN")
        if (unknownIndex != -1){
            reader.skipValue()
            return constants[unknownIndex]
        }

        val otherIndex: Int = nameStrings.indexOf("OTHER")
        if (otherIndex != -1) {
            reader.skipValue()
            return constants[otherIndex]
        }

        if (!useFallbackValue) {
            val name = reader.nextString()
            throw JsonDataException(
                "Expected one of "
                        + listOf(*nameStrings) + " but was " + name + " at path " + path
            )
        }
        if (reader.peek() != JsonReader.Token.STRING) {
            throw JsonDataException(
                "Expected a string but was " + reader.peek() + " at path " + path
            )
        }
        reader.skipValue()
        return fallbackValue
    }

    @Throws(IOException::class)
    override fun toJson(writer: JsonWriter, value: T?) {
        if (value == null) {
            throw NullPointerException(
                "value was null! Wrap in .nullSafe() to write nullable values."
            )
        }
        writer.value(nameStrings[value.ordinal])
    }

    override fun toString(): String {
        return "EnumJsonAdapter(" + enumType.name + ")"
    }

    companion object {
        fun <T : Enum<*>> create(enumType: Class<T>): TinkEnumJsonAdapter<T> {
            return TinkEnumJsonAdapter(enumType, null, false)
        }
    }

    init {
        try {
            constants = enumType.enumConstants
            nameStrings = arrayOfNulls(constants.size)
            for (i in constants.indices) {
                val constantName = constants[i].name
                val annotation =
                    enumType.getField(constantName).getAnnotation(
                        Json::class.java
                    )
                val name =
                    annotation?.name ?: constantName
                nameStrings[i] = name
            }
            options = JsonReader.Options.of(*nameStrings)
        } catch (e: NoSuchFieldException) {
            throw AssertionError("Missing field in " + enumType.name, e)
        }
    }
}