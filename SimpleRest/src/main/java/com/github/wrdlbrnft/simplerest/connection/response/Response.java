package com.github.wrdlbrnft.simplerest.connection.response;

import java.net.URL;
import java.util.Map;

/**
 * Created with Android Studio<br>
 * User: Xaver<br>
 * Date: 20/11/2016
 */
public interface Response {
    int getStatus();
    String getData();
    Cookies getCookies();
    Map<String, String> getHeaders();
    String getHeaderValue(String name);
    URL getLocation();
}
