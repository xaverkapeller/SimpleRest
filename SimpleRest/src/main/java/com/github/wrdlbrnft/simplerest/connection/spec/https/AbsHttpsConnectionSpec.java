package com.github.wrdlbrnft.simplerest.connection.spec.https;

import com.github.wrdlbrnft.simplerest.connection.spec.ConnectionSpec;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

/**
 * Created with Android Studio<br>
 * User: Xaver<br>
 * Date: 20/11/2016
 */
public abstract class AbsHttpsConnectionSpec implements ConnectionSpec {

    @Override
    public HttpURLConnection openConnection(String urlString) throws GeneralSecurityException, IOException {
        final KeyManager[] keyManagers = createKeyManagers();
        final TrustManager[] trustManagers = createTrustManagers();
        final SSLContext sslContext = createSSLContext(keyManagers, trustManagers);
        return openConnectionWithSSLContext(sslContext, urlString);
    }

    protected abstract KeyManager[] createKeyManagers() throws UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException;
    protected abstract TrustManager[] createTrustManagers() throws NoSuchAlgorithmException, KeyStoreException;

    private SSLContext createSSLContext(KeyManager[] keyManagers, TrustManager[] trustManagers) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, UnrecoverableKeyException {
        final SSLContext context = SSLContext.getInstance("TLS");
        context.init(keyManagers, trustManagers, null);
        return context;
    }

    private HttpURLConnection openConnectionWithSSLContext(SSLContext context, String urlString) throws IOException {
        final URL url = new URL(urlString);
        final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        if (connection instanceof HttpsURLConnection) {
            final HttpsURLConnection httpsConnection = (HttpsURLConnection) connection;
            httpsConnection.setSSLSocketFactory(context.getSocketFactory());
        }
        return connection;
    }
}
