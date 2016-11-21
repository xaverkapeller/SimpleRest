package com.github.wrdlbrnft.simplerest.callbacks;

import com.github.wrdlbrnft.simplerest.util.StatusCode;

/**
 * Created with Android Studio<br>
 * User: Xaver<br>
 * Date: 20/11/2016
 */
public interface ResultCallback<T> {
    void onResult(@StatusCode int statusCode, T result);
}
