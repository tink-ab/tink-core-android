package com.tink.service.misc

import com.tink.model.misc.ExactNumber
import java.math.BigDecimal

internal fun Double.toExactNumber() =
    BigDecimal(this).toExactNumber()

internal fun BigDecimal.toExactNumber() =
    ExactNumber(unscaledValue().toLong(), scale().toLong())

internal fun ExactNumber.toDouble() =
    BigDecimal.valueOf(unscaledValue, scale.toInt()).toDouble()
