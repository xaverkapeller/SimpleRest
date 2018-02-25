package com.github.wrdlbrnft.simplerest.connection.response;

import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * Created with Android Studio<br>
 * User: Xaver<br>
 * Date: 20/11/2016
 */
public interface Response {
    int getStatus();
    byte[] getData();
    String getDataAsString();
    String getDataAsString(Charset charset);
    Cookies getCookies();
    Map<String, String> getHeaders();
    String getHeaderValue(String name);
    URL getLocation();
}
