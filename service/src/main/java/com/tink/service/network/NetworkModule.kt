package com.tink.service.network

import android.content.Context
import android.net.Uri
import com.tink.service.authentication.UserEventBus
import dagger.Module
import dagger.Provides
import io.grpc.Channel
import io.grpc.ClientInterceptors
import io.grpc.okhttp.OkHttpChannelBuilder
import java.io.ByteArrayInputStream
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    internal fun provideInterceptor(
        tinkConfig: TinkConfiguration,
        userEventBus: UserEventBus
    ): HeaderClientInterceptor {
        return HeaderClientInterceptor(
            tinkConfig.oAuthClientId,
            userEventBus
        )
    }

    @Provides
    @Singleton
    internal fun provideChannel(
        tinkConfig: TinkConfiguration,
        interceptor: HeaderClientInterceptor,
        applicationContext: Context
    ): Channel {
        val channel =
            OkHttpChannelBuilder
                .forAddress(tinkConfig.environment.grpcUrl, tinkConfig.environment.port)
                .apply {
                    tinkConfig.environment.grpcSSLKey?.let { sslKey ->
                        sslSocketFactory(
                            TLSHelper.getSslSocketFactory(
                                ByteArrayInputStream(
                                    sslKey.toByteArray()
                                )
                            )
                        )
                    }
                }
                .build()

        ChannelConnector(applicationContext, channel).keepConnected()

        return ClientInterceptors.intercept(channel, interceptor)
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
    val grpcUrl: String,
    val restUrl: String,
    val port: Int,
    val grpcSSLKey: String? = null,
    val restSSLKey: String? = null
) {

    object Production : Environment(
        grpcUrl = "main-grpc.production.oxford.tink.se",
        restUrl = "https://api.tink.com",
        port = 443,
        grpcSSLKey = "-----BEGIN CERTIFICATE-----\n" +
                "MIIEkjCCA3qgAwIBAgIQCgFBQgAAAVOFc2oLheynCDANBgkqhkiG9w0BAQsFADA/\n" +
                "MSQwIgYDVQQKExtEaWdpdGFsIFNpZ25hdHVyZSBUcnVzdCBDby4xFzAVBgNVBAMT\n" +
                "DkRTVCBSb290IENBIFgzMB4XDTE2MDMxNzE2NDA0NloXDTIxMDMxNzE2NDA0Nlow\n" +
                "SjELMAkGA1UEBhMCVVMxFjAUBgNVBAoTDUxldCdzIEVuY3J5cHQxIzAhBgNVBAMT\n" +
                "GkxldCdzIEVuY3J5cHQgQXV0aG9yaXR5IFgzMIIBIjANBgkqhkiG9w0BAQEFAAOC\n" +
                "AQ8AMIIBCgKCAQEAnNMM8FrlLke3cl03g7NoYzDq1zUmGSXhvb418XCSL7e4S0EF\n" +
                "q6meNQhY7LEqxGiHC6PjdeTm86dicbp5gWAf15Gan/PQeGdxyGkOlZHP/uaZ6WA8\n" +
                "SMx+yk13EiSdRxta67nsHjcAHJyse6cF6s5K671B5TaYucv9bTyWaN8jKkKQDIZ0\n" +
                "Z8h/pZq4UmEUEz9l6YKHy9v6Dlb2honzhT+Xhq+w3Brvaw2VFn3EK6BlspkENnWA\n" +
                "a6xK8xuQSXgvopZPKiAlKQTGdMDQMc2PMTiVFrqoM7hD8bEfwzB/onkxEz0tNvjj\n" +
                "/PIzark5McWvxI0NHWQWM6r6hCm21AvA2H3DkwIDAQABo4IBfTCCAXkwEgYDVR0T\n" +
                "AQH/BAgwBgEB/wIBADAOBgNVHQ8BAf8EBAMCAYYwfwYIKwYBBQUHAQEEczBxMDIG\n" +
                "CCsGAQUFBzABhiZodHRwOi8vaXNyZy50cnVzdGlkLm9jc3AuaWRlbnRydXN0LmNv\n" +
                "bTA7BggrBgEFBQcwAoYvaHR0cDovL2FwcHMuaWRlbnRydXN0LmNvbS9yb290cy9k\n" +
                "c3Ryb290Y2F4My5wN2MwHwYDVR0jBBgwFoAUxKexpHsscfrb4UuQdf/EFWCFiRAw\n" +
                "VAYDVR0gBE0wSzAIBgZngQwBAgEwPwYLKwYBBAGC3xMBAQEwMDAuBggrBgEFBQcC\n" +
                "ARYiaHR0cDovL2Nwcy5yb290LXgxLmxldHNlbmNyeXB0Lm9yZzA8BgNVHR8ENTAz\n" +
                "MDGgL6AthitodHRwOi8vY3JsLmlkZW50cnVzdC5jb20vRFNUUk9PVENBWDNDUkwu\n" +
                "Y3JsMB0GA1UdDgQWBBSoSmpjBH3duubRObemRWXv86jsoTANBgkqhkiG9w0BAQsF\n" +
                "AAOCAQEA3TPXEfNjWDjdGBX7CVW+dla5cEilaUcne8IkCJLxWh9KEik3JHRRHGJo\n" +
                "uM2VcGfl96S8TihRzZvoroed6ti6WqEBmtzw3Wodatg+VyOeph4EYpr/1wXKtx8/\n" +
                "wApIvJSwtmVi4MFU5aMqrSDE6ea73Mj2tcMyo5jMd6jmeWUHK8so/joWUoHOUgwu\n" +
                "X4Po1QYz+3dszkDqMp4fklxBwXRsW10KXzPMTZ+sOPAveyxindmjkW8lGy+QsRlG\n" +
                "PfZ+G6Z6h7mjem0Y+iWlkYcV4PIWL1iwBi8saCbGS5jN2p8M+X+Q7UNKEkROb3N6\n" +
                "KOqkqm57TH2H3eDJAkSnh6/DNFu0Qg==\n" +
                "-----END CERTIFICATE-----",
        restSSLKey = "-----BEGIN CERTIFICATE-----\n" +
                "MIIGeDCCBWCgAwIBAgISA/I1VxCbScdt47kFS63VetEFMA0GCSqGSIb3DQEBCwUA\n" +
                "MEoxCzAJBgNVBAYTAlVTMRYwFAYDVQQKEw1MZXQncyBFbmNyeXB0MSMwIQYDVQQD\n" +
                "ExpMZXQncyBFbmNyeXB0IEF1dGhvcml0eSBYMzAeFw0xOTA4MTMwNjUzNDhaFw0x\n" +
                "OTExMTEwNjUzNDhaMBcxFTATBgNVBAMTDGFwaS50aW5rLmNvbTCCAiIwDQYJKoZI\n" +
                "hvcNAQEBBQADggIPADCCAgoCggIBAO9VBtV415cjUEOTibyUx0ILszmRpaj1UF4i\n" +
                "FppB8pYyB0zNwKUZqH3vbbyEXXnjjj/7HRKNLOpNgXnPhkDWIz2rlUDK0Mz1jBXD\n" +
                "wtICmW4Xhio20b3pJEE1LMkRv59H3jsxF5MrCgukdKZ44hw9yqyMMVv10ye1tB9Q\n" +
                "fJDu/sZQT85ztRfmRvXceSvv6EfUVPQByKR9oi220oJqUBWB2HjNMj/G/SXprxq9\n" +
                "y/pGDLUsiGtofVsU1V0/jskxKCmOJGYPABPavvGdMRyxswUB84eZUxdWOWa8tndT\n" +
                "mLJD7x9T7TqQAigLyRlROJXukRtNoXwDzskenFQTY28Iq0Xtttm9FuHoLP3DWVQ2\n" +
                "OQ7CrW5/HlhvJyyza9F0c0ldh2mXlqOpxOkJnVs7sCSQUolC2qGLWjh++B6kW0fu\n" +
                "T5RN50Ghh/jSv4afAXA+9mSmqk7T9TUIpsWPMh8LQQNXzjpDLhTeTjM/AIOja6NU\n" +
                "qaS+WIEe4FJm+c0BFi0zd+J2XwN2+JsiQhPbb3oASKnBvw5/KQblFPTdaAf5Bz3+\n" +
                "hVusiDxfvClp9YrCDfjbfqmT06O9u44BddKmd0CH3oeiZqwLNDjoUjwHzV7lszGh\n" +
                "7vPKsC3WpAjjgKTPsIdMFubS4NKOIKHxILLSd+31j7dYF9DH3lRPGLzpOC6CNwAA\n" +
                "zb48DyaLAgMBAAGjggKJMIIChTAOBgNVHQ8BAf8EBAMCBaAwHQYDVR0lBBYwFAYI\n" +
                "KwYBBQUHAwEGCCsGAQUFBwMCMAwGA1UdEwEB/wQCMAAwHQYDVR0OBBYEFMR+Okdu\n" +
                "tEBsEwWtYgjWtaTV37E2MB8GA1UdIwQYMBaAFKhKamMEfd265tE5t6ZFZe/zqOyh\n" +
                "MG8GCCsGAQUFBwEBBGMwYTAuBggrBgEFBQcwAYYiaHR0cDovL29jc3AuaW50LXgz\n" +
                "LmxldHNlbmNyeXB0Lm9yZzAvBggrBgEFBQcwAoYjaHR0cDovL2NlcnQuaW50LXgz\n" +
                "LmxldHNlbmNyeXB0Lm9yZy8wPgYDVR0RBDcwNYIlYXBpLWdhdGV3YXkucHJvZHVj\n" +
                "dGlvbi5veGZvcmQudGluay5zZYIMYXBpLnRpbmsuY29tMEwGA1UdIARFMEMwCAYG\n" +
                "Z4EMAQIBMDcGCysGAQQBgt8TAQEBMCgwJgYIKwYBBQUHAgEWGmh0dHA6Ly9jcHMu\n" +
                "bGV0c2VuY3J5cHQub3JnMIIBBQYKKwYBBAHWeQIEAgSB9gSB8wDxAHYA4mlLribo\n" +
                "6UAJ6IYbtjuD1D7n/nSI+6SPKJMBnd3x2/4AAAFsifgE3QAABAMARzBFAiEAlHuA\n" +
                "7SHsS7+dx0TlNL+k/DKqTF5iRoTyX5/AcGGvhwoCIC+fh04UfP9unAKpqXQsjZri\n" +
                "JzRyd6kF0DT41xM9ib0fAHcAY/Lbzeg7zCzPC3KEJ1drM6SNYXePvXWmOLHHaFRL\n" +
                "2I0AAAFsifgE/wAABAMASDBGAiEAqsgqCcgKeRvVUgHFY38a3/GqzlvZjJyNZl7k\n" +
                "E2NMWXoCIQCYIYFYlh9od9F4d7wwBfdv5Laki03zEgTZB3YEAAO6kDANBgkqhkiG\n" +
                "9w0BAQsFAAOCAQEAXHZ/tliSQOu+ZbM7OmnQg+GqEvRhd0wQzrcVv0gv/XtXtn0W\n" +
                "LNPz30MPluXrHUd/OHbF1ITaTqHylpvhSegH6wlXrfkfrFJjHbo9Detl4Pl7OhAM\n" +
                "VXeh0p7Sj/XP3zl7oXOUwTZbbiBRnFZRRcWo8aYYKOSw4BN4VbGbVSh4eAvw1OTf\n" +
                "FQF1F8GfI2qWmj4Zzyj2rzOtOVr7uSE1Dh2quAzmNYF/++x++yymjbvMUJzFLbQF\n" +
                "wN+4fkWdfm52naf2ee/rggxyjUBQpX28fPehxyuRee+cYrEeFkdXlLpepxkeKmMk\n" +
                "gHxwu2y5CcKwNwBmXVoC5pfHM9HDvCwKdbsENw==\n" +
                "-----END CERTIFICATE-----\n"
    )

    class Custom(
        grpcApiUrl: String,
        restApiUrl: String,
        portNumber: Int,
        grpcApiSSLKey: String?,
        restApiSSLKey: String?
    ) : Environment(
        grpcUrl = grpcApiUrl,
        restUrl = restApiUrl,
        port = portNumber,
        grpcSSLKey = grpcApiSSLKey,
        restSSLKey = restApiSSLKey
    )
}
