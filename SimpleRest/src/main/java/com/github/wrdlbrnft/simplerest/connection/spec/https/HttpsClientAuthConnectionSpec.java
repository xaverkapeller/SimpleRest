package com.github.wrdlbrnft.simplerest.connection.spec.https;

import com.github.wrdlbrnft.simplerest.connection.spec.ConnectionSpec;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

/**
 * Created by kapeller on 22/05/15.
 */
public class HttpsClientAuthConnectionSpec extends AbsHttpsConnectionSpec implements ConnectionSpec {

    private final KeyStore mServerKeyStore;

    private final KeyStore mClientKeyStore;
    private final char[] mClientKeyStorePassword;

    public HttpsClientAuthConnectionSpec(KeyStore serverKeyStore, KeyStore clientKeyStore, char[] clientKeyStorePassword) {
        mServerKeyStore = serverKeyStore;
        mClientKeyStore = clientKeyStore;
        mClientKeyStorePassword = clientKeyStorePassword;
    }

    @Override
    protected KeyManager[] createKeyManagers() throws UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException {
        final String keyManagerFactoryAlgorithm = KeyManagerFactory.getDefaultAlgorithm();
        final KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(keyManagerFactoryAlgorithm);
        keyManagerFactory.init(mClientKeyStore, mClientKeyStorePassword);
        return keyManagerFactory.getKeyManagers();
    }

    @Override
    protected TrustManager[] createTrustManagers() throws NoSuchAlgorithmException, KeyStoreException {
        final String trustManagerFactoryAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
        final TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(trustManagerFactoryAlgorithm);
        trustManagerFactory.init(mServerKeyStore);
        return trustManagerFactory.getTrustManagers();
    }
}
