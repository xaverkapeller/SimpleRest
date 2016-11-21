package com.github.wrdlbrnft.simplerest.connection.request;

import android.support.v4.util.ArrayMap;
import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
        DELETE("DELETE", false, true);

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
    String getData();
    boolean shouldFollowRedirects();
    Map<String, String> getHeaders();
    List<QueryParameter> getQueryParameters();

    class Builder {

        public static final String HEADER_NAME_COOKIES = "Cookie";
        public static final String HEADER_NAME_CONTENT_TYPE = "Content-Type";
        private final Map<String, String> mHeaderMap = new ArrayMap<>();
        private final List<QueryParameter> mQueryParameters = new ArrayList<>();
        private final List<String> mCookies = new ArrayList<>();

        private Method mMethod = Method.GET;
        private String mRelativeUrl = "";
        private String mData = null;
        private boolean mFollowRedirects = true;

        public Builder() {
        }

        public Builder setMethod(Method method) {
            mMethod = method;
            return this;
        }

        public Builder setRelativeUrl(String relativeUrl) {
            mRelativeUrl = relativeUrl;
            return this;
        }

        public Builder setData(String data) {
            mHeaderMap.put("Content-Length", data != null ? String.valueOf(data.length()) : null);
            mData = data;
            return this;
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
            try {
                mQueryParameters.add(new QueryParameterImpl(key, URLEncoder.encode(value, "UTF-8")));
            } catch (UnsupportedEncodingException e) {
                throw new IllegalStateException("UTF-8 seems to be unsupported on this device?", e);
            }
            return this;
        }

        public Request build() {
            if (!mCookies.isEmpty()) {
                mHeaderMap.put(HEADER_NAME_COOKIES, TextUtils.join(";", mCookies));
            }
            return new RequestImpl(mMethod, mRelativeUrl, mQueryParameters, mFollowRedirects, mData, mHeaderMap);
        }
    }
}
