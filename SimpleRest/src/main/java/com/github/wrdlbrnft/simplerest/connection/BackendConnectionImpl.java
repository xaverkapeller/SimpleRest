package com.github.wrdlbrnft.simplerest.connection;

import android.support.annotation.NonNull;

import com.github.wrdlbrnft.simplerest.connection.exception.BackendConnectionException;
import com.github.wrdlbrnft.simplerest.connection.request.QueryParameter;
import com.github.wrdlbrnft.simplerest.connection.request.Request;
import com.github.wrdlbrnft.simplerest.connection.spec.ConnectionSpec;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Map;

/**
 * Created with Android Studio<br>
 * User: Xaver<br>
 * Date: 20/11/2016
 */
class BackendConnectionImpl extends AbsBackendConnection {

    private final String mEndpointUrl;
    private final ConnectionSpec mConnectionSpec;

    public BackendConnectionImpl(String endpointUrl, ConnectionSpec connectionSpec) {
        mEndpointUrl = endpointUrl;
        mConnectionSpec = connectionSpec;
    }

    @Override
    protected HttpURLConnection performConnect(Request request) throws BackendConnectionException {

        try {
            final String urlString = createUrl(request);

            final HttpURLConnection connection = mConnectionSpec.openConnection(urlString);
            connection.setInstanceFollowRedirects(request.shouldFollowRedirects());

            final Map<String, String> headers = request.getHeaders();
            for (String key : headers.keySet()) {
                connection.setRequestProperty(key, headers.get(key));
            }

            connection.setUseCaches(false);

            final Request.Method method = request.getMethod();
            connection.setDoOutput(method.doesOutput());
            connection.setDoInput(method.doesInput());
            connection.setRequestMethod(method.getHttpRepresentation());

            connection.connect();

            return connection;
        } catch (GeneralSecurityException | IOException e) {
            throw new BackendConnectionException("Connection to backend failed!", e);
        }
    }

    @NonNull
    private String createUrl(Request request) {
        final StringBuilder builder = new StringBuilder();

        builder.append(mEndpointUrl).append(request.getRelativeUrl());

        final List<QueryParameter> queryParameters = request.getQueryParameters();
        for (int i = 0, count = queryParameters.size(); i < count; i++) {
            final QueryParameter parameter = queryParameters.get(i);

            builder.append(i == 0 ? "?" : "&");
            builder.append(parameter.getKey()).append("=").append(parameter.getValue());
        }

        return builder.toString();
    }
}
