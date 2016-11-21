package com.github.wrdlbrnft.simplerest.certificates;

import android.content.Context;

import com.github.wrdlbrnft.simplerest.exceptions.KeystoreCreationException;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

/**
 * Created with Android Studio<br>
 * User: Xaver<br>
 * Date: 20/11/2016
 */
public class KeyStores {

    public enum Type {
        BKS("BKS"),
        PKCS12("PKCS12");

        private final String mName;

        Type(String name) {
            mName = name;
        }

        @Override
        public String toString() {
            return mName;
        }
    }

    private static KeyStore newKeyStore() {
        try {
            final String keyStoreType = KeyStore.getDefaultType();
            return KeyStore.getInstance(keyStoreType);
        } catch (KeyStoreException e) {
            throw new KeystoreCreationException("Could not create new KeyStore!", e);
        }
    }

    public static KeyStore createEmptyKeyStore() {
        try {
            KeyStore keyStore = newKeyStore();
            keyStore.load(null, null);
            return keyStore;
        } catch (IOException | NoSuchAlgorithmException | CertificateException e) {
            throw new KeystoreCreationException("Could not create empty KeyStore!", e);
        }
    }

    public static KeyStore fromRawResource(Context context, String fileName, Type type, String certificatePassword) {
        final RawResourceReader reader = new RawResourceReader(context);
        final InputStream stream = reader.openStream(fileName);
        return fromInputStream(stream, type, certificatePassword);
    }

    public static KeyStore fromInputStream(InputStream certificateStream, Type type, String certificatePassword) {
        try {
            KeyStore keyStore = KeyStore.getInstance(type.toString());
            keyStore.load(certificateStream, certificatePassword.toCharArray());
            return keyStore;
        } catch (KeyStoreException | IOException | CertificateException | NoSuchAlgorithmException e) {
            throw new KeystoreCreationException("Could not create KeyStore from InputStream!", e);
        }
    }
}
