package com.github.wrdlbrnft.simplerest.exceptions;

/**
 * Created by kapeller on 23/07/15.
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
