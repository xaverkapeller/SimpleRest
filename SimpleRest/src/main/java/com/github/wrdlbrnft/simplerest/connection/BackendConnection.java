package com.github.wrdlbrnft.simplerest.connection;

import com.github.wrdlbrnft.simplerest.connection.request.Request;
import com.github.wrdlbrnft.simplerest.connection.response.Response;
import com.github.wrdlbrnft.simplerest.connection.spec.ConnectionSpec;
import com.github.wrdlbrnft.simplerest.connection.spec.http.HttpConnectionSpec;
import com.github.wrdlbrnft.simplerest.util.SimpleRestUtils;

/**
 * Created with Android Studio<br>
 * User: Xaver<br>
 * Date: 20/11/2016
 */
public interface BackendConnection {

    interface Observer {
        default void onPerformingRequest(Request request) {}
        default void onResponseReceived(Response response) {}
        default void onRequestFailed(Throwable throwable) {}
        default void onRequestProcessed(Request request, Response response, Throwable error) {}
    }

    Response perform(Request request);

    void registerObserver(Observer observer);
    void unregisterObserver(Observer observer);

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
