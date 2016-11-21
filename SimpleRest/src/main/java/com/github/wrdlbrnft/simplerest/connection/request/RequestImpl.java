package com.github.wrdlbrnft.simplerest.connection.request;

import java.util.List;
import java.util.Map;

/**
 * Created with Android Studio<br>
 * User: Xaver<br>
 * Date: 20/11/2016
 */
class RequestImpl implements Request {

    private final Method mMethod;
    private final String mRelativeUrl;
    private final List<QueryParameter> mQueryParameters;
    private final boolean mFollowRedirects;
    private final String mData;
    private final Map<String, String> mHeaderMap;

    public RequestImpl(Method method, String relativeUrl, List<QueryParameter> queryParameters, boolean followRedirects, String data, Map<String, String> headerMap) {
        mMethod = method;
        mRelativeUrl = relativeUrl;
        mQueryParameters = queryParameters;
        mFollowRedirects = followRedirects;
        mData = data;
        mHeaderMap = headerMap;
    }

    @Override
    public Method getMethod() {
        return mMethod;
    }

    @Override
    public String getRelativeUrl() {
        return mRelativeUrl;
    }

    public List<QueryParameter> getQueryParameters() {
        return mQueryParameters;
    }

    @Override
    public String getData() {
        return mData;
    }

    @Override
    public boolean shouldFollowRedirects() {
        return mFollowRedirects;
    }

    @Override
    public Map<String, String> getHeaders() {
        return mHeaderMap;
    }
}
