package com.github.wrdlbrnft.simplerest.connection;

import com.github.wrdlbrnft.simplerest.connection.request.Request;
import com.github.wrdlbrnft.simplerest.connection.response.Response;
import com.github.wrdlbrnft.simplerest.connection.spec.ConnectionSpec;
import com.github.wrdlbrnft.simplerest.connection.spec.http.HttpConnectionSpec;
import com.github.wrdlbrnft.simplerest.util.SimpleRestUtils;

import java.net.HttpURLConnection;

/**
 * Created by kapeller on 16/04/15.
 */
public interface BackendConnection {

    Response perform(Request request);
    HttpURLConnection openConnection(Request request);

    class Builder {

        private static final ConnectionSpec DEFAULT_CONNECTION_SPEC = new HttpConnectionSpec();

        private String mEndpointUrl;
        private ConnectionSpec mConnectionSpec = DEFAULT_CONNECTION_SPEC;

        public Builder setEndpointUrl(String endpointUrl) {
            mEndpointUrl = endpointUrl;
            return this;
        }

        public Builder setConnectionSpec(ConnectionSpec connectionSpec) {
            mConnectionSpec = connectionSpec;
            return this;
        }

        public BackendConnection build() {
            return new BackendConnectionImpl(
                    SimpleRestUtils.requireNotNull(mEndpointUrl, "You have to specify an endpoint url for a BackendConnection."),
                    SimpleRestUtils.requireNotNull(mConnectionSpec, "You have to specify a valid ConnectionSpec for a BackendConnection.")
            );
        }
    }
}
