package com.github.wrdlbrnft.simplerest.connection.response;

import java.util.List;

/**
 * Created with Android Studio<br>
 * User: Xaver<br>
 * Date: 20/11/2016
 */
public interface Cookies {
    List<String> getAll();
    boolean hasKeyValuePair(String key);
    String getValueForKey(String key);
}
