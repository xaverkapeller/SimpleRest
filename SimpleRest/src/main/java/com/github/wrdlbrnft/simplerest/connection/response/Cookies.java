package com.github.wrdlbrnft.simplerest.connection.response;

import java.util.List;

/**
 * Created by kapeller on 03/12/15.
 */
public interface Cookies {
    List<String> getAll();
    boolean hasKeyValuePair(String key);
    String getValueForKey(String key);
}
