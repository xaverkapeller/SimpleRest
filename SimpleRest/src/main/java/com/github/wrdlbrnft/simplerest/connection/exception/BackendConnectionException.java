package com.github.wrdlbrnft.simplerest.connection.exception;

/**
 * Created with Android Studio<br>
 * User: Xaver<br>
 * Date: 20/11/2016
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
