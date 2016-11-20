package com.github.wrdlbrnft.simplerest.connection;

import com.github.wrdlbrnft.simplerest.connection.response.Cookies;

import java.util.List;
import java.util.Map;

/**
 * Created by kapeller on 03/12/15.
 */
class CookiesImpl implements Cookies {

    private final List<String> mCookies;
    private final Map<String, String> mKeyValueCookies;

    public CookiesImpl(List<String> cookies, Map<String, String> keyValueCookies) {
        mCookies = cookies;
        mKeyValueCookies = keyValueCookies;
    }

    @Override
    public List<String> getAll() {
        return mCookies;
    }

    @Override
    public boolean hasKeyValuePair(String key) {
        return mKeyValueCookies.containsKey(key);
    }

    @Override
    public String getValueForKey(String key) {
        return mKeyValueCookies.get(key);
    }

    @Override
    public String toString() {
        return "CookiesImpl{" +
                "mCookies=" + mCookies +
                '}';
    }
}
