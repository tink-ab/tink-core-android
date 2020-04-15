package com.tink.service.misc

import com.tink.model.Images

internal typealias ImagesDTO = com.tink.service.generated.models.ImageUrls

internal fun ImagesDTO.toImages() =
    Images(
        icon = icon.orEmpty(),
        banner = icon.orEmpty()
    )