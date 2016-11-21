package com.github.wrdlbrnft.simplerest.connection.spec.https;

import com.github.wrdlbrnft.simplerest.connection.spec.ConnectionSpec;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

import javax.net.ssl.KeyManager;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

/**
 * Created with Android Studio<br>
 * User: Xaver<br>
 * Date: 20/11/2016
 */
public class ServerCertificateConnectionSpec extends AbsHttpsConnectionSpec implements ConnectionSpec {

    private final KeyStore mServerKeyStore;

    public ServerCertificateConnectionSpec(KeyStore serverKeyStore) {
        mServerKeyStore = serverKeyStore;
    }

    @Override
    protected KeyManager[] createKeyManagers() throws UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException {
        return new KeyManager[0];
    }

    @Override
    protected TrustManager[] createTrustManagers() throws NoSuchAlgorithmException, KeyStoreException {
        final String trustManagerFactoryAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
        final TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(trustManagerFactoryAlgorithm);
        trustManagerFactory.init(mServerKeyStore);
        return trustManagerFactory.getTrustManagers();
    }
}
