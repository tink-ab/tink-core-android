package com.tink.rest.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @property iconUrl A URL the client can optionally use to show an icon to represent the option.
 * @property text The human-readable description of this option to display to the user.
 * @property value The machine-readable value to send if the user picks this option.
 */
@JsonClass(generateAdapter = true)
data class SelectOption(
    @Json(name = "iconUrl") @field:Json(name = "iconUrl") var iconUrl: String? = null,
    @Json(name = "text") @field:Json(name = "text") var text: String? = null,
    @Json(name = "value") @field:Json(name = "value") var value: String? = null
)
