package com.tink.service.misc

import com.tink.model.misc.Field
import com.tink.rest.models.Field as FieldDTO

fun List<Field>.toFieldMap() = map { it.name to it.value }.toMap()

fun FieldDTO.toCoreModel(): Field {
    return Field(
        name = name ?: "",
        value = value ?: "",
        validationRules = Field.ValidationRules(
            maxLength = maxLength ?: 0,
            minLength = minLength ?: 0,
            pattern = pattern ?: "",
            patternError = patternError ?: "",
            isOptional = optional ?: false
        ),
        attributes = Field.Attributes(
            description = description ?: "",
            hint = hint ?: "",
            helpText = helpText ?: "",
            inputType = Field.InputType(
                isMasked = masked ?: false,
                isNumeric = numeric ?: false,
                isImmutable = immutable ?: false
            )
        )
    )
}
