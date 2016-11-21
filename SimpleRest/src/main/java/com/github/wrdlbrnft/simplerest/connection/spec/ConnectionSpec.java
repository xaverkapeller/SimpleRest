package com.github.wrdlbrnft.simplerest.connection.spec;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.security.GeneralSecurityException;

/**
 * Created with Android Studio<br>
 * User: Xaver<br>
 * Date: 20/11/2016
 */
public interface ConnectionSpec {
    HttpURLConnection openConnection(String urlString) throws GeneralSecurityException, IOException;
}
