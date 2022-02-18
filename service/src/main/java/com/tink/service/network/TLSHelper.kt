package com.tink.service.network

import java.io.InputStream
import java.security.KeyStore
import java.security.NoSuchAlgorithmException
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

internal object TLSHelper {

    @Throws(Exception::class)
    private fun getTrustManagers(ca: InputStream): Array<TrustManager> {
        val ks = KeyStore.getInstance(KeyStore.getDefaultType())
        ks.load(null)
        val cf = CertificateFactory.getInstance("X.509")
        val cert = cf.generateCertificate(ca) as X509Certificate
        val principal = cert.subjectX500Principal
        ks.setCertificateEntry(principal.getName("RFC2253"), cert)
        // Set up trust manager factory to use our key store.
        val trustManagerFactory =
            TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
        trustManagerFactory.init(ks)
        return trustManagerFactory.trustManagers
    }

    @Throws(Exception::class)
    fun getSslSocketFactory(ca: InputStream?): SSLSocketFactory {
        if (ca == null) {
            return SSLSocketFactory.getDefault() as SSLSocketFactory
        }

        val sslContext: SSLContext = try {
            SSLContext.getInstance("TLSv1.3")
        } catch (e: NoSuchAlgorithmException) {
            SSLContext.getInstance("TLSv1.2")
        }
        sslContext.init(null, getTrustManagers(ca), null)
        return sslContext.socketFactory
    }

    fun getFirstTrustManager(ca: InputStream): X509TrustManager {
        val trustManagers = getTrustManagers(ca)
        check(trustManagers.size == 1 && trustManagers[0] is X509TrustManager) {
            ("Unexpected default trust managers: ${trustManagers.contentToString()}")
        }
        return trustManagers[0] as X509TrustManager
    }
}
