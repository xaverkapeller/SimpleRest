package com.github.wrdlbrnft.simplerest.exceptions;

/**
 * Created by kapeller on 23/07/15.
 */
public class CertificateCreationException extends RuntimeException {

    public CertificateCreationException(String detailMessage) {
        super(detailMessage);
    }

    public CertificateCreationException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public CertificateCreationException(Throwable throwable) {
        super(throwable);
    }
}
