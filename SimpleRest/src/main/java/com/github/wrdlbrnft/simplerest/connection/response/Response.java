package com.github.wrdlbrnft.simplerest.connection.response;

import java.net.URL;
import java.util.Map;

/**
 * Created by kapeller on 17/04/15.
 */
public interface Response {
    int getStatus();
    String getData();
    Cookies getCookies();
    Map<String, String> getHeaders();
    String getHeaderValue(String name);
    URL getLocation();
}
