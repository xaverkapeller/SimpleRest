package com.github.wrdlbrnft.simplerest.exceptions;

/**
 * Created with Android Studio<br>
 * User: Xaver<br>
 * Date: 20/11/2016
 */
public class KeystoreCreationException extends RuntimeException {

    public KeystoreCreationException(String detailMessage) {
        super(detailMessage);
    }

    public KeystoreCreationException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public KeystoreCreationException(Throwable throwable) {
        super(throwable);
    }
}
