package com.github.wrdlbrnft.simplerest.certificates;

import android.content.Context;

import com.github.wrdlbrnft.simplerest.exceptions.CertificateCreationException;

import java.io.IOException;
import java.io.InputStream;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

/**
 * Created by kapeller on 22/05/15.
 */
public class Certificates {

    private Certificates() {
    }

    public static Certificate fromRawResource(Context context, String fileName) {
        final RawResourceReader reader = new RawResourceReader(context);
        final InputStream stream = reader.openStream(fileName);
        return fromInputStream(stream);
    }

    public static Certificate fromInputStream(InputStream stream) {
        try {
            final CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            final Certificate certificate = certificateFactory.generateCertificate(stream);
            stream.close();
            return certificate;
        } catch (CertificateException | IOException e) {
            throw new CertificateCreationException(e);
        }
    }
}
