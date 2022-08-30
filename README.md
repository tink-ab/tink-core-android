# Tink Core

Tink Core is an internal Tink module used in [Tink Link](https://github.com/tink-ab/tink-link-android) and [Money Manager](https://github.com/tink-ab/tink-money-manager-android). It is not intended to be used outside of these projects, but in the spirit of transparency it's open source, for those who wish to understand the fundamentals of Tink's SDKs.

## Installation

Tink Core is published on [Maven Central](https://repo1.maven.org/maven2/com/tink/core/), but you can also install it in your local maven repository:

1. Build the artifact: `assemble`
2. Install in your local maven repository: `publishToMavenLocal`
3. Add `mavenLocal()` where you want to use this (needs to be above `MavenCentral()` as otherwise the dependency will be fetched from Maven Central)
4. Add core as a dependency `api ("com.tink:core:<version>")`

## [Tink](https://tink.com)
Tink was founded in 2012 with the aim of changing the banking industry for the better. We have built Europe’s most robust open banking platform – with the broadest, deepest connectivity and powerful services that create value out of the financial data.

## Support
👋 We are continuously working on improving the developer experience of our API offering. [Contact us](https://tinkab.atlassian.net/servicedesk/customer/portal/5) for support, questions or suggestions.