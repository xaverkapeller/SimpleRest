package com.github.wrdlbrnft.simplerest.exceptions;

/**
 * Created with Android Studio<br>
 * User: Xaver<br>
 * Date: 20/11/2016
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
