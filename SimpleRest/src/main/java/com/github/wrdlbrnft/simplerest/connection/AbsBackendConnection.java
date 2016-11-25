package com.github.wrdlbrnft.simplerest.connection;

import android.support.v4.util.ArrayMap;
import android.util.Log;

import com.github.wrdlbrnft.simplerest.connection.exception.BackendConnectionException;
import com.github.wrdlbrnft.simplerest.connection.request.Request;
import com.github.wrdlbrnft.simplerest.connection.response.Cookies;
import com.github.wrdlbrnft.simplerest.connection.response.Response;
import com.github.wrdlbrnft.simplerest.util.LogUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created with Android Studio<br>
 * User: Xaver<br>
 * Date: 20/11/2016
 */
public abstract class AbsBackendConnection implements BackendConnection {

    private static final String LOG_TAG = AbsBackendConnection.class.getSimpleName();

    @Override
    public Response perform(Request request) {

        final LogUtils.Block block = LogUtils.newBlock();

        try {
            LogUtils.logRequest(block, request);
            final HttpURLConnection connection = openConnection(request);
            final Response response = readResponse(connection);
            LogUtils.logResponse(block, response);

            return response;
        } catch (IOException e) {
            throw new BackendConnectionException("Connection to backend failed!", e);
        } finally {
            final String log = block.build();
            Log.i(LOG_TAG, "|\n" + log);
        }
    }

    private Response readResponse(HttpURLConnection connection) throws IOException {
        final int status = connection.getResponseCode();
        final Map<String, String> headers = parseResponseHeaders(connection);
        final Cookies cookies = parseCookieFromHeaders(headers);
        final String responseData = readResponseData(connnection);
        return new ResponseImpl(status, responseData, headers, cookies, connection.getURL());
    }
    
    private String readResponseData(HttpURLConnection connection) throws IOException {
        if(connection.getDoInput()) {
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
                    Collections.<String>emptyList(),
                    Collections.<String, String>emptyMap()
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

    @Override
    public HttpURLConnection openConnection(Request request) throws BackendConnectionException {
        try {
            final String json = request.getData();
            final HttpURLConnection connection = performConnect(request);

            if (json != null && json.length() > 0) {
                final OutputStream outputStream = connection.getOutputStream();
                writeDataToStream(outputStream, json);
            }

            return connection;
        } catch (IOException e) {
            throw new BackendConnectionException("Connection to backend failed!", e);
        }
    }

    private void writeDataToStream(OutputStream stream, String data) throws IOException {
        final Writer writer = new OutputStreamWriter(stream);
        writer.write(data);
        writer.flush();
        writer.close();
    }

    private String readDataFromStream(InputStream stream) throws IOException {
        final Reader reader = new InputStreamReader(stream);
        final BufferedReader bufferedReader = new BufferedReader(reader);

        final StringBuilder builder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            builder.append(line);
        }

        return builder.toString();
    }

    protected abstract HttpURLConnection performConnect(Request request) throws BackendConnectionException;
}
