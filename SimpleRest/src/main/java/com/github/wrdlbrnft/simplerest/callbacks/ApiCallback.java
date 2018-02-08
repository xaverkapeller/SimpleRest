package com.github.wrdlbrnft.simplerest.callbacks;

import com.github.wrdlbrnft.simplerest.util.StatusCode;

/**
 * Created with Android Studio<br>
 * User: Xaver<br>
 * Date: 20/11/2016
 */
public interface ApiCallback<T> {
    void onResult(@StatusCode int statusCode, T result);
    void onError(Throwable throwable);

    abstract class Adapter<T> implements ApiCallback<T> {

        @Override
        public void onResult(@StatusCode int statusCode, T result) {

        }

        @Override
        public void onError(Throwable throwable) {

        }
    }
}
