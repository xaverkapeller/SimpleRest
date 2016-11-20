package com.github.wrdlbrnft.simplerest.connection;

import com.github.wrdlbrnft.simplerest.connection.request.Request;
import com.github.wrdlbrnft.simplerest.connection.response.Response;
import com.github.wrdlbrnft.simplerest.connection.spec.ConnectionSpec;

import java.net.HttpURLConnection;

/**
 * Created by kapeller on 16/04/15.
 */
public interface BackendConnection {

    Response perform(Request request);
    HttpURLConnection openConnection(Request request);

    class Factory {

        private Factory() {
        }

        public static BackendConnection newInstance(String endpointUrl, ConnectionSpec spec) {
            return new BackendConnectionImpl(endpointUrl, spec);
        }
    }
}
