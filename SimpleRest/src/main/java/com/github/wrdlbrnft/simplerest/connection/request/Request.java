package com.github.wrdlbrnft.simplerest.connection.request;

import android.support.v4.util.ArrayMap;
import android.text.TextUtils;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with Android Studio<br>
 * User: Xaver<br>
 * Date: 20/11/2016
 */
public interface Request {

    enum Method {
        POST("POST", true, true),
        GET("GET", false, true),
        PUT("PUT", true, true),
        DELETE("DELETE", false, true),
        HEAD("HEAD", false, false);

        private final String mHttpRepresentation;
        private final boolean mDoesOutput;
        private final boolean mDoesInput;

        Method(String httpRepresentation, boolean doesOutput, boolean doesInput) {
            mHttpRepresentation = httpRepresentation;
            mDoesOutput = doesOutput;
            mDoesInput = doesInput;
        }

        public String getHttpRepresentation() {
            return mHttpRepresentation;
        }

        public boolean doesOutput() {
            return mDoesOutput;
        }

        public boolean doesInput() {
            return mDoesInput;
        }

        @Override
        public String toString() {
            return mHttpRepresentation;
        }
    }

    Method getMethod();
    String getRelativeUrl();
    byte[] getData();
    boolean shouldFollowRedirects();
    Map<String, String> getHeaders();
    List<QueryParameter> getQueryParameters();
    List<String> getPathSegments();

    class Builder {

        public static final String HEADER_NAME_COOKIES = "Cookie";
        public static final String HEADER_NAME_CONTENT_TYPE = "Content-Type";
        private final Map<String, String> mHeaderMap = new ArrayMap<>();
        private final List<QueryParameter> mQueryParameters = new ArrayList<>();
        private final List<String> mCookies = new ArrayList<>();
        private final List<String> mPathParameters = new ArrayList<>();

        private Method mMethod = Method.GET;
        private String mRelativeUrl = "";
        private byte[] mData = null;
        private boolean mFollowRedirects = true;

        public Builder setMethod(Method method) {
            mMethod = method;
            return this;
        }

        public Builder setRelativeUrl(String relativeUrl) {
            if(relativeUrl.startsWith("/")) {
                return setRelativeUrl(relativeUrl.substring(1));
            }
            mRelativeUrl = relativeUrl;
            return this;
        }

        public Builder setData(byte[] data) {
            mHeaderMap.put("Content-Length", data != null ? String.valueOf(data.length) : null);
            mData = data;
            return this;
        }

        public Builder setData(String data) {
            final byte[] bytes = data.getBytes(Charset.forName("UTF-8"));
            return setData(bytes);
        }

        public Builder setContentType(String contentType) {
            mHeaderMap.put(HEADER_NAME_CONTENT_TYPE, contentType);
            return this;
        }

        public Builder setFollowRedirects(boolean followRedirects) {
            mFollowRedirects = followRedirects;
            return this;
        }

        public Builder addHeader(String key, String value) {
            mHeaderMap.put(key, value);
            return this;
        }

        public Builder addCookie(String value) {
            mCookies.add(value);
            return this;
        }

        public Builder addCookie(String key, String value) {
            mCookies.add(key + "=" + value);
            return this;
        }

        public Builder addQueryParameter(String key, String value) {
            mQueryParameters.add(new QueryParameterImpl(key, value));
            return this;
        }

        public Builder addPathParameter(String path) {
            if (path.startsWith("/")) {
                return addPathParameter(path.substring(1));
            }
            mPathParameters.add(path);
            return this;
        }

        public Request build() {
            if (!mCookies.isEmpty()) {
                mHeaderMap.put(HEADER_NAME_COOKIES, TextUtils.join(";", mCookies));
            }
            return new RequestImpl(mMethod, mRelativeUrl, mPathParameters, mQueryParameters, mFollowRedirects, mData, mHeaderMap);
        }
    }
}
