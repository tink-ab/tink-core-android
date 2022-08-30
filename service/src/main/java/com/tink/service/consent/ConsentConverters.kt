package com.tink.service.consent

import com.tink.model.consent.OAuthClientDescription
import com.tink.model.consent.ScopeDescription
import com.tink.rest.apis.DescribeOAuth2ClientResponse
import com.tink.rest.apis.ScopeDescription as ScopeDescriptionDTO

internal fun DescribeOAuth2ClientResponse.toOAuthClientDescription() =
    OAuthClientDescription(
        clientName = clientName,
        clientUrl = clientUrl,
        clientIconUrl = clientIconUrl,
        verified = verified,
        aggregator = aggregator,
        scopeDescriptions = scopeDescriptions.map { it.toCoreModel() }
    )

internal fun ScopeDescriptionDTO.toCoreModel() = ScopeDescription(title, description)
