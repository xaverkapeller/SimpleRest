package com.github.wrdlbrnft.simplerest.connection;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;

import com.github.wrdlbrnft.simplerest.connection.exception.BackendConnectionException;
import com.github.wrdlbrnft.simplerest.connection.request.QueryParameter;
import com.github.wrdlbrnft.simplerest.connection.request.Request;
import com.github.wrdlbrnft.simplerest.connection.response.Cookies;
import com.github.wrdlbrnft.simplerest.connection.response.Response;
import com.github.wrdlbrnft.simplerest.connection.spec.ConnectionSpec;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import android.util.Log;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created with Android Studio<br>
 * User: Xaver<br>
 * Date: 20/11/2016
 */
class BackendConnectionImpl implements BackendConnection {

    private static final String TAG = "BackendConnectionImpl";

    private final String mEndpointUrl;
    private final ConnectionSpec mConnectionSpec;

    private final List<Observer> mObservers = new CopyOnWriteArrayList<>();

    public BackendConnectionImpl(String endpointUrl, ConnectionSpec connectionSpec) {
        mEndpointUrl = endpointUrl;
        mConnectionSpec = connectionSpec;
    }

    @Override
    public Response perform(Request request) {

        Response response = null;
        Throwable error = null;
        try {
            notifyPerformingRequest(request);
            final byte[] data = request.getData();
            final HttpURLConnection connection = performConnect(request);

            if (data != null) {
                final OutputStream outputStream = connection.getOutputStream();
                outputStream.write(data);
                outputStream.flush();
                outputStream.close();
            }

            response = readResponse(connection);
            notifyResponseReceived(response);
            return response;
        } catch (Exception e) {
            error = e;
            notifyRequestFailed(e);
            throw new BackendConnectionException("Connection to backend failed!", e);
        } finally {
            notifyRequestProcessed(request, response, error);
        }
    }

    private void notifyPerformingRequest(Request request) {
        for (Observer observer : mObservers) {
            observer.onPerformingRequest(request);
        }
    }

    private void notifyResponseReceived(Response response) {
        for (Observer observer : mObservers) {
            observer.onResponseReceived(response);
        }
    }

    private void notifyRequestFailed(Exception e) {
        for (Observer observer : mObservers) {
            observer.onRequestFailed(e);
        }
    }

    private void notifyRequestProcessed(Request request, Response response, Throwable error) {
        for (Observer observer : mObservers) {
            observer.onRequestProcessed(request, response, error);
        }
    }

    @Override
    public void registerObserver(Observer observer) {
        mObservers.add(observer);
    }

    @Override
    public void unregisterObserver(Observer observer) {
        mObservers.remove(observer);
    }

    private Response readResponse(HttpURLConnection connection) throws IOException {
        final int status = connection.getResponseCode();
        final Map<String, String> headers = parseResponseHeaders(connection);
        final Cookies cookies = parseCookieFromHeaders(headers);
        final byte[] data = readResponseData(connection);
        return new ResponseImpl(
                status,
                data,
                headers,
                cookies,
                connection.getURL()
        );
    }

    private byte[] readResponseData(HttpURLConnection connection) throws IOException {
        if (connection.getDoInput()) {
            final InputStream inputStream = connection.getResponseCode() < 400
                    ? connection.getInputStream()
                    : connection.getErrorStream();
            return readDataFromStream(inputStream);
        }
        return null;
    }

    private Cookies parseCookieFromHeaders(Map<String, String> headers) {
        final String cookieString = headers.get("Set-Cookie");
        if (cookieString == null) {
            return new CookiesImpl(
                    Collections.emptyList(),
                    Collections.emptyMap()
            );
        }

        final List<String> cookies = Arrays.asList(cookieString.split(";"));
        final Map<String, String> keyValueCookies = new ArrayMap<>();
        for (String cookie : cookies) {
            if (!cookie.contains("=")) {
                continue;
            }

            final String[] split = cookie.split("=");
            keyValueCookies.put(split[0], split[1]);
        }

        return new CookiesImpl(cookies, keyValueCookies);
    }

    private Map<String, String> parseResponseHeaders(HttpURLConnection connection) {
        final Map<String, String> headers = new ArrayMap<>();

        final Map<String, List<String>> headerFields = connection.getHeaderFields();
        for (String key : headerFields.keySet()) {
            final List<String> values = headerFields.get(key);
            if (values.size() > 1) {
                continue;
            }

            headers.put(key, values.get(0));
        }

        return headers;
    }

    private byte[] readDataFromStream(InputStream stream) throws IOException {

        final byte[] buffer = new byte[4096];

        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        int n;
        while ((n = stream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, n);
        }

        return outputStream.toByteArray();
    }

    private HttpURLConnection performConnect(Request request) throws BackendConnectionException {

        try {
            final String urlString = createUrl(request);
            
            Log.i(TAG, "Opening Connection to: " + urlString);

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

        final Uri.Builder builder = Uri.parse(mEndpointUrl)
                .buildUpon()
                .appendPath(request.getRelativeUrl());

        for (String segment : request.getPathSegments()) {
            builder.appendPath(segment);
        }

        for (QueryParameter parameter : request.getQueryParameters()) {
            builder.appendQueryParameter(parameter.getKey(), parameter.getValue());
        }

        return builder.build().toString();
    }
}
