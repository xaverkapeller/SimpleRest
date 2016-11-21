package com.github.wrdlbrnft.simplerest.connection.spec.http;

import com.github.wrdlbrnft.simplerest.connection.spec.ConnectionSpec;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.GeneralSecurityException;

/**
 * Created with Android Studio<br>
 * User: Xaver<br>
 * Date: 20/11/2016
 */
public class HttpConnectionSpec implements ConnectionSpec {

    @Override
    public HttpURLConnection openConnection(String urlString) throws GeneralSecurityException, IOException {
        final URL url = new URL(urlString);
        return (HttpURLConnection) url.openConnection();
    }
}
