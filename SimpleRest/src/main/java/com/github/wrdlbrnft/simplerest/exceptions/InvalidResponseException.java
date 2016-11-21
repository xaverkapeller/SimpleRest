package com.github.wrdlbrnft.simplerest.exceptions;

import android.text.TextUtils;

import com.github.wrdlbrnft.simplerest.connection.response.Response;

/**
 * Created with Android Studio<br>
 * User: Xaver<br>
 * Date: 20/11/2016
 */
public class InvalidResponseException extends ApiCallFailedException {

    private final Response mResponse;

    public InvalidResponseException(String detailMessage, Response response) {
        super(formatMessage(detailMessage, response));
        mResponse = response;
    }

    public InvalidResponseException(String detailMessage, Throwable throwable, Response response) {
        super(formatMessage(detailMessage, response), throwable);
        mResponse = response;
    }

    public InvalidResponseException(Throwable throwable, Response response) {
        super(formatMessage(null, response), throwable);
        mResponse = response;
    }

    public Response getResponse() {
        return mResponse;
    }

    private static String formatMessage(String detailMessage, Response response) {
        final StringBuilder builder = new StringBuilder();

        if (!TextUtils.isEmpty(detailMessage)) {
            builder.append(detailMessage);
        }

        builder.append("\nInvalid Response: ").append(response);

        return builder.toString();
    }
}
