package com.github.wrdlbrnft.simplerest.exceptions;

/**
 * Created with Android Studio<br>
 * User: Xaver<br>
 * Date: 20/11/2016
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
