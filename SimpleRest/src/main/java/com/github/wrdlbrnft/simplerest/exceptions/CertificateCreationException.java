package com.github.wrdlbrnft.simplerest.exceptions;

/**
 * Created with Android Studio<br>
 * User: Xaver<br>
 * Date: 20/11/2016
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
