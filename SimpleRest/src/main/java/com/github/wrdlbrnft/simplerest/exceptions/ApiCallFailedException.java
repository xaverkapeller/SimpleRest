package com.github.wrdlbrnft.simplerest.exceptions;

/**
 * Created by kapeller on 25/11/15.
 */
public class ApiCallFailedException extends RuntimeException {

    public ApiCallFailedException(String detailMessage) {
        super(detailMessage);
    }

    public ApiCallFailedException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public ApiCallFailedException(Throwable throwable) {
        super(throwable);
    }
}
