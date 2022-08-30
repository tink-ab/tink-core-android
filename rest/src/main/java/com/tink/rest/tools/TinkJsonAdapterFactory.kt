package com.tink.rest.tools

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.tink.rest.models.Credentials
import java.lang.reflect.Type

class TinkJsonAdapterFactory : JsonAdapter.Factory {
    override fun create(
        type: Type,
        annotations: MutableSet<out Annotation>,
        moshi: Moshi
    ): JsonAdapter<*>? {
        if (type == Credentials.SupplementalInfoWrapper::class.java) {
            return SupplementalInformationWrapperJsonAdapter(moshi)
        }
        return null
    }
}
