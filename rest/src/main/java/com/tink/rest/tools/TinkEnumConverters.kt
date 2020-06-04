package com.tink.rest.tools

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.EnumJsonAdapter
import com.tink.rest.models.Provider

object TinkEnumConverters {

    val allEnumTypes = mapOf(
        Provider.CapabilitiesEnum::class.java to Provider.CapabilitiesEnum.UNKNOWN
    )
}

fun Moshi.Builder.addAllTinkEnumAdapters(): Moshi.Builder {
    for ((type, fallback) in TinkEnumConverters.allEnumTypes) {
        add(type, EnumJsonAdapter.create(type).withUnknownFallback(fallback))
    }
    return this
}

