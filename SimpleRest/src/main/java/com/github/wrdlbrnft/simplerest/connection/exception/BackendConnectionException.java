package com.github.wrdlbrnft.simplerest.connection.exception;

/**
 * Created by kapeller on 29/09/15.
 */
public class BackendConnectionException extends RuntimeException {

    public BackendConnectionException(String detailMessage) {
        super(detailMessage);
    }

    public BackendConnectionException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public BackendConnectionException(Throwable throwable) {
        super(throwable);
    }
}
