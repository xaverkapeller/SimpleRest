package com.github.wrdlbrnft.simplerest.exceptions;

/**
 * Created by kapeller on 16/07/15.
 */
public class SimpleRestException extends RuntimeException {

    public SimpleRestException(String detailMessage) {
        super(detailMessage);
    }

    public SimpleRestException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public SimpleRestException(Throwable throwable) {
        super(throwable);
    }
}
