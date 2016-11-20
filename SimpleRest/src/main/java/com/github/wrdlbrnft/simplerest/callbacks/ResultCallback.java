package com.github.wrdlbrnft.simplerest.callbacks;

import com.github.wrdlbrnft.simplerest.util.StatusCode;

/**
 * Created by kapeller on 27/04/16.
 */
public interface ResultCallback<T> {
    void onResult(@StatusCode int statusCode, T result);
}
