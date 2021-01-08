package com.tink.service.misc

import com.tink.model.misc.Amount
import com.tink.model.misc.ExactNumber
import com.tink.rest.models.CurrencyDenominatedAmount

fun CurrencyDenominatedAmount.toAmount() =
    Amount(ExactNumber(unscaledValue, scale.toLong()), currencyCode)

fun Amount.toDto() =
    CurrencyDenominatedAmount(value.unscaledValue, value.scale.toInt(), currencyCode)
