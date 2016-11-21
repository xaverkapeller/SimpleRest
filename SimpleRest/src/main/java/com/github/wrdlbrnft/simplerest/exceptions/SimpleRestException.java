package com.github.wrdlbrnft.simplerest.exceptions;

/**
 * Created with Android Studio<br>
 * User: Xaver<br>
 * Date: 20/11/2016
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
