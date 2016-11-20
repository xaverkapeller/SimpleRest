package com.github.wrdlbrnft.simplerest.connection;

import com.github.wrdlbrnft.simplerest.connection.response.Cookies;
import com.github.wrdlbrnft.simplerest.connection.response.Response;

import java.net.URL;
import java.util.Map;

/**
 * Created by kapeller on 03/12/15.
 */
class ResponseImpl implements Response {

    private final int mStatus;
    private final String mData;
    private final Map<String, String> mHeaderMap;
    private final Cookies mCookies;
    private final URL mLocation;

    public ResponseImpl(int status, String data, Map<String, String> headerMap, Cookies cookies, URL location) {
        mStatus = status;
        mData = data;
        mHeaderMap = headerMap;
        mCookies = cookies;
        mLocation = location;
    }

    @Override
    public int getStatus() {
        return mStatus;
    }

    @Override
    public String getData() {
        return mData;
    }

    @Override
    public Cookies getCookies() {
        return mCookies;
    }

    @Override
    public Map<String, String> getHeaders() {
        return mHeaderMap;
    }

    @Override
    public String getHeaderValue(String name) {
        return mHeaderMap.get(name);
    }

    public URL getLocation() {
        return mLocation;
    }

    @Override
    public String toString() {
        return "ResponseImpl{" +
                "mStatus=" + mStatus +
                ", mData='" + mData + '\'' +
                ", mHeaderMap=" + mHeaderMap +
                ", mCookies=" + mCookies +
                '}';
    }
}
