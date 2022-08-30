package com.tink.service.misc

import com.tink.model.Images

internal typealias ImagesDTO = com.tink.rest.models.ImageUrls

internal fun ImagesDTO.toImages() =
    Images(
        icon = icon.orEmpty(),
        banner = banner.orEmpty()
    )
