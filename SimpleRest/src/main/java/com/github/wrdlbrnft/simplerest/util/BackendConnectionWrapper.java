package com.github.wrdlbrnft.simplerest.util;

import android.support.v4.util.ArrayMap;

import com.github.wrdlbrnft.simplerest.connection.BackendConnection;
import com.github.wrdlbrnft.simplerest.connection.request.Request;
import com.github.wrdlbrnft.simplerest.connection.response.Cookies;
import com.github.wrdlbrnft.simplerest.connection.response.Response;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by kapeller on 02/12/15.
 */
public class BackendConnectionWrapper implements BackendConnection {

    private static final BackendConnection DUMMY_CONNECTION = new DummyBackendConnection();

    private BackendConnection mConnection = DUMMY_CONNECTION;

    @Override
    public Response perform(Request request) {
        return mConnection.perform(request);
    }

    @Override
    public HttpURLConnection openConnection(Request request) {
        return mConnection.openConnection(request);
    }

    public void setConnection(BackendConnection connection) {
        mConnection = connection != null ? connection : DUMMY_CONNECTION;
    }

    private static class DummyBackendConnection implements BackendConnection {

        private static final DummyConnection DUMMY_HTTP_CONNECTION = new DummyConnection();

        @Override
        public Response perform(Request request) {
            return new DummyResponseImpl(StatusCodes.ClientError.NOT_FOUND, null);
        }

        @Override
        public HttpURLConnection openConnection(Request request) {
            return DUMMY_HTTP_CONNECTION;
        }
    }

    private static class DummyResponseImpl implements Response {

        private final int mStatus;
        private final String mData;
        private final Cookies mCookies = new DummyCookies();
        private final Map<String, String> mHeaders = Collections.unmodifiableMap(new ArrayMap<String, String>());

        public DummyResponseImpl(int status, String data) {
            mStatus = status;
            mData = data;
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
            return mHeaders;
        }

        @Override
        public String getHeaderValue(String name) {
            return null;
        }

        @Override
        public URL getLocation() {
            return null;
        }

        @Override
        public String toString() {
            return "Dummy Response! Not registered yet.";
        }
    }

    private static class DummyConnection extends HttpURLConnection {

        protected DummyConnection() {
            super(null);
        }

        @Override
        public void disconnect() {

        }

        @Override
        public boolean usingProxy() {
            return false;
        }

        @Override
        public void connect() throws IOException {

        }
    }

    private static class DummyCookies implements Cookies {

        private final List<String> mCookiesLib = new ArrayList<>();

        @Override
        public List<String> getAll() {
            return mCookiesLib;
        }

        @Override
        public boolean hasKeyValuePair(String key) {
            return false;
        }

        @Override
        public String getValueForKey(String key) {
            return null;
        }
    }
}
