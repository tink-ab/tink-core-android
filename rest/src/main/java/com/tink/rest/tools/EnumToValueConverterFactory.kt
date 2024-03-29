package com.tink.rest.tools

import com.squareup.moshi.Json
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

internal class EnumToValueConverterFactory : Converter.Factory() {

    private val enumConverter = EnumToValueConverter()

    override fun stringConverter(type: Type, annotations: Array<out Annotation>, retrofit: Retrofit): Converter<*, String>? {
        return if (type is Class<*> && type.isEnum) {
            enumConverter
        } else {
            null
        }
    }

    internal class EnumToValueConverter : Converter<Any, String> {
        override fun convert(enum: Any): String? {
            val enumName = (enum as Enum<*>).name
            val jsonAnnotation: Json? = enum.javaClass.getField(enumName).getAnnotation(Json::class.java)

            // Checking if the Enum is annotated with @Json to get the name.
            // If not, fallback to enum default (.toString())
            return jsonAnnotation?.name ?: enum.toString()
        }
    }
}
