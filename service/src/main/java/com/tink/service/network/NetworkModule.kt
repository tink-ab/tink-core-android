package com.tink.service.network

import android.net.Uri
import com.tink.service.authentication.UserEventBus
import com.tink.service.di.ServiceScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import org.conscrypt.Conscrypt
import java.io.ByteArrayInputStream
import java.security.Security

@Module
internal class NetworkModule {

    init {
        insertConscryptSecurityProvider()
    }

    @Provides
    @ServiceScope
    internal fun provideOkHttpClient(
        tinkConfiguration: TinkConfiguration,
        userEventBus: UserEventBus
    ): OkHttpClient =
        OkHttpClient.Builder()
            .apply {
                tinkConfiguration.environment.restSSLKey?.let { sslKey ->
                    sslSocketFactory(
                        TLSHelper.getSslSocketFactory(ByteArrayInputStream(sslKey.toByteArray())),
                        TLSHelper.getFirstTrustManager(ByteArrayInputStream(sslKey.toByteArray()))
                    )
                }
                addInterceptor(
                    HeaderInterceptor(
                        tinkConfiguration.oAuthClientId,
                        userEventBus,
                        null
                    )
                )
            }
            .build()

    /**
     * Use Conscrypt TLS implementation
     *
     * See [OkHttp docs](https://square.github.io/okhttp/#requirements)
     */
    private fun insertConscryptSecurityProvider() {
        Security.insertProviderAt(Conscrypt.newProvider(), 1)
    }
}

/**
 * Configuration for Tink Link
 * @param environment The environment you want to connect to. Use [Environment.Production] if you
 * are using the Tink environment or add your own with [Environment.Custom]
 * @param oAuthClientId Your client id. You can retrieve it from [the Tink console][https://console.tink.com].
 * @param redirectUri The URI to redirect back to your app from a browser or third party app.
 * Refer to the [third party authorization guide][https://github.com/tink-ab/tink-link-sdk-android/blob/master/third-party-authentication.md] for details.
 */
data class TinkConfiguration(
    val environment: Environment,
    val oAuthClientId: String,
    val redirectUri: Uri
) {
    init {
        with(redirectUri) {
            require(!scheme.isNullOrEmpty() && !authority.isNullOrEmpty()) {
                "Both scheme and authority needs to be set for redirectUri."
            }
        }
    }
}

sealed class Environment(
    val restUrl: String,
    val restSSLKey: String? = null
) {

    object Production : Environment(
        restUrl = "https://api.tink.com",
        restSSLKey = "-----BEGIN CERTIFICATE-----\n" +
                "MIIEZTCCA02gAwIBAgIQQAF1BIMUpMghjISpDBbN3zANBgkqhkiG9w0BAQsFADA/\n" +
                "MSQwIgYDVQQKExtEaWdpdGFsIFNpZ25hdHVyZSBUcnVzdCBDby4xFzAVBgNVBAMT\n" +
                "DkRTVCBSb290IENBIFgzMB4XDTIwMTAwNzE5MjE0MFoXDTIxMDkyOTE5MjE0MFow\n" +
                "MjELMAkGA1UEBhMCVVMxFjAUBgNVBAoTDUxldCdzIEVuY3J5cHQxCzAJBgNVBAMT\n" +
                "AlIzMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuwIVKMz2oJTTDxLs\n" +
                "jVWSw/iC8ZmmekKIp10mqrUrucVMsa+Oa/l1yKPXD0eUFFU1V4yeqKI5GfWCPEKp\n" +
                "Tm71O8Mu243AsFzzWTjn7c9p8FoLG77AlCQlh/o3cbMT5xys4Zvv2+Q7RVJFlqnB\n" +
                "U840yFLuta7tj95gcOKlVKu2bQ6XpUA0ayvTvGbrZjR8+muLj1cpmfgwF126cm/7\n" +
                "gcWt0oZYPRfH5wm78Sv3htzB2nFd1EbjzK0lwYi8YGd1ZrPxGPeiXOZT/zqItkel\n" +
                "/xMY6pgJdz+dU/nPAeX1pnAXFK9jpP+Zs5Od3FOnBv5IhR2haa4ldbsTzFID9e1R\n" +
                "oYvbFQIDAQABo4IBaDCCAWQwEgYDVR0TAQH/BAgwBgEB/wIBADAOBgNVHQ8BAf8E\n" +
                "BAMCAYYwSwYIKwYBBQUHAQEEPzA9MDsGCCsGAQUFBzAChi9odHRwOi8vYXBwcy5p\n" +
                "ZGVudHJ1c3QuY29tL3Jvb3RzL2RzdHJvb3RjYXgzLnA3YzAfBgNVHSMEGDAWgBTE\n" +
                "p7Gkeyxx+tvhS5B1/8QVYIWJEDBUBgNVHSAETTBLMAgGBmeBDAECATA/BgsrBgEE\n" +
                "AYLfEwEBATAwMC4GCCsGAQUFBwIBFiJodHRwOi8vY3BzLnJvb3QteDEubGV0c2Vu\n" +
                "Y3J5cHQub3JnMDwGA1UdHwQ1MDMwMaAvoC2GK2h0dHA6Ly9jcmwuaWRlbnRydXN0\n" +
                "LmNvbS9EU1RST09UQ0FYM0NSTC5jcmwwHQYDVR0OBBYEFBQusxe3WFbLrlAJQOYf\n" +
                "r52LFMLGMB0GA1UdJQQWMBQGCCsGAQUFBwMBBggrBgEFBQcDAjANBgkqhkiG9w0B\n" +
                "AQsFAAOCAQEA2UzgyfWEiDcx27sT4rP8i2tiEmxYt0l+PAK3qB8oYevO4C5z70kH\n" +
                "ejWEHx2taPDY/laBL21/WKZuNTYQHHPD5b1tXgHXbnL7KqC401dk5VvCadTQsvd8\n" +
                "S8MXjohyc9z9/G2948kLjmE6Flh9dDYrVYA9x2O+hEPGOaEOa1eePynBgPayvUfL\n" +
                "qjBstzLhWVQLGAkXXmNs+5ZnPBxzDJOLxhF2JIbeQAcH5H0tZrUlo5ZYyOqA7s9p\n" +
                "O5b85o3AM/OJ+CktFBQtfvBhcJVd9wvlwPsk+uyOy2HI7mNxKKgsBTt375teA2Tw\n" +
                "UdHkhVNcsAKX1H7GNNLOEADksd86wuoXvg==\n" +
                "-----END CERTIFICATE-----\n"
    )

    class Custom(
        restApiUrl: String,
        restApiSSLKey: String?
    ) : Environment(
        restUrl = restApiUrl,
        restSSLKey = restApiSSLKey
    )
}
