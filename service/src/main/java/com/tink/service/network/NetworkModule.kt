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
                tinkConfiguration.environment.sslCertificate?.let { sslKey ->
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

/**
 * Represents the environment you want to connect to.
 *
 * @property restUrl The url for the REST endpoint
 * @property sslCertificate An optional ssl pinning certificate associated with the [restUrl]
 */
sealed class Environment(
    val restUrl: String,
    val sslCertificate: String? = null
) {

    /**
     * Represents the Tink production environment
     */
    object Production : Environment(restUrl = "https://api.tink.com")

    /**
     * Represents a custom environment you want to connect to.
     */
    class Custom(
        restUrl: String,
        sslCertificate: String? = null
    ) : Environment(
        restUrl = restUrl,
        sslCertificate = sslCertificate
    )
}

/**
 * Creates an environment with the [Environment.Production] rest URL and the specified [sslCertificate].
 */
fun Environment.Production.withSslKey(sslCertificate: String) =
    Environment.Custom(
        restUrl = "https://api.tink.com",
        sslCertificate = sslCertificate
    )
