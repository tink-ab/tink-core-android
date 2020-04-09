package com.tink.service.consent

import com.tink.model.consent.OAuthClientDescription
import com.tink.model.consent.ScopeDescription
import com.tink.service.generated.apis.DescribeOAuth2ClientResponse
import com.tink.service.generated.apis.ScopeDescription as ScopeDescriptionDTO

fun DescribeOAuth2ClientResponse.toOAuthClientDescription() =
    OAuthClientDescription(
        clientName = clientName,
        clientUrl = clientUrl,
        clientIconUrl = clientIconUrl,
        embeddedAllowed = embeddedAllowed,
        verified = verified,
        aggregator = aggregator,
        scopeDescriptions = scopeDescriptions.map { it.toCoreModel() }
    )

fun ScopeDescriptionDTO.toCoreModel() = ScopeDescription(title, description)