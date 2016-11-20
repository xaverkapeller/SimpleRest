package com.github.wrdlbrnft.simplerest.tasks;

import com.github.wrdlbrnft.simplerest.util.StatusCode;

/**
 * Created by kapeller on 08/01/16.
 */
public interface Result<T> {
    @StatusCode
    int getStatusCode();
    T getData();
}
