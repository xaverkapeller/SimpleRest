package com.github.wrdlbrnft.simplerest.connection;

import com.github.wrdlbrnft.simplerest.connection.response.Cookies;
import com.github.wrdlbrnft.simplerest.connection.response.Response;

import java.net.URL;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Map;

/**
 * Created with Android Studio<br>
 * User: Xaver<br>
 * Date: 20/11/2016
 */
class ResponseImpl implements Response {

    private final int mStatus;
    private final byte[] mData;
    private final Map<String, String> mHeaderMap;
    private final Cookies mCookies;
    private final URL mLocation;

    public ResponseImpl(int status, byte[] data, Map<String, String> headerMap, Cookies cookies, URL location) {
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
    public byte[] getData() {
        return mData;
    }

    @Override
    public String getDataAsString() {
        return getDataAsString(Charset.forName("UTF-8"));
    }

    @Override
    public String getDataAsString(Charset charset) {
        return new String(mData, charset);
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
                ", mData=" + Arrays.toString(mData) +
                ", mHeaderMap=" + mHeaderMap +
                ", mCookies=" + mCookies +
                ", mLocation=" + mLocation +
                '}';
    }
}
