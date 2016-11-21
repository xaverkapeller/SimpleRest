package com.github.wrdlbrnft.simplerest.util;

/**
 * Created with Android Studio<br>
 * User: Xaver<br>
 * Date: 20/11/2016
 */
public class SimpleRestUtils {

    public static <T> T requireNotNull(T value) {
        if (value == null) {
            throw new NullPointerException();
        }
        return value;
    }

    public static <T> T requireNotNull(T value, String message) {
        if (value == null) {
            throw new NullPointerException(message);
        }
        return value;
    }
}
