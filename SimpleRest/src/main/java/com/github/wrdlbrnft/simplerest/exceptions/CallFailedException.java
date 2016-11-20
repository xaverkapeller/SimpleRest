package com.github.wrdlbrnft.simplerest.exceptions;

/**
 * Created by kapeller on 30/09/15.
 */
public class CallFailedException extends RuntimeException {

    private final int mStatusCode;

    public CallFailedException(String detailMessage, int statusCode) {
        super(detailMessage);
        mStatusCode = statusCode;
    }

    public CallFailedException(String detailMessage, Throwable throwable, int statusCode) {
        super(detailMessage, throwable);
        mStatusCode = statusCode;
    }

    public CallFailedException(Throwable throwable, int statusCode) {
        super(throwable);
        mStatusCode = statusCode;
    }
}
