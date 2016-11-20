package com.github.wrdlbrnft.simplerest.callbacks;

import com.github.wrdlbrnft.simplerest.util.StatusCode;

/**
 * Created by kapeller on 26/11/15.
 */
public interface ApiCallback<T> {
    void onResult(@StatusCode int statusCode, T result);
    void onError();

    abstract class Adapter<T> implements ApiCallback<T> {

        @Override
        public void onResult(@StatusCode int statusCode, T result) {

        }

        @Override
        public void onError() {

        }
    }
}
