package com.tink.service.misc

import com.tink.model.misc.Field

fun List<Field>.toFieldMap() = map { it.name to it.value }.toMap()
