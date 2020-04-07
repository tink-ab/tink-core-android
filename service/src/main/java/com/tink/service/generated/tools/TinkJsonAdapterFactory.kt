package com.tink.service.generated.tools

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.tink.service.credentials.SupplementalInformationWrapperJsonAdapter
import com.tink.service.generated.models.Credentials
import java.lang.reflect.Type

class TinkJsonAdapterFactory : JsonAdapter.Factory {
    override fun create(
        type: Type,
        annotations: MutableSet<out Annotation>,
        moshi: Moshi
    ): JsonAdapter<*>? {
        return if (type == Credentials.SupplementalInfoWrapper::class.java) {
            SupplementalInformationWrapperJsonAdapter(moshi)
        } else null
    }
}
