package com.github.wrdlbrnft.simplerest.connection.spec.http;

import com.github.wrdlbrnft.simplerest.connection.spec.ConnectionSpec;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.GeneralSecurityException;

/**
 * Created by kapeller on 22/05/15.
 */
public class HttpConnectionSpec implements ConnectionSpec {

    @Override
    public HttpURLConnection openConnection(String urlString) throws GeneralSecurityException, IOException {
        final URL url = new URL(urlString);
        return (HttpURLConnection) url.openConnection();
    }
}
