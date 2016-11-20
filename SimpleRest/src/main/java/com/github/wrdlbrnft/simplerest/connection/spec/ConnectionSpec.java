package com.github.wrdlbrnft.simplerest.connection.spec;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.security.GeneralSecurityException;

/**
 * Created by kapeller on 22/05/15.
 */
public interface ConnectionSpec {
    HttpURLConnection openConnection(String urlString) throws GeneralSecurityException, IOException;
}
