package com.github.wrdlbrnft.simplerest.tasks;

import com.github.wrdlbrnft.simplerest.callbacks.ApiCallback;
import com.github.wrdlbrnft.simplerest.callbacks.ErrorCallback;
import com.github.wrdlbrnft.simplerest.callbacks.ResultCallback;

/**
 * Created by kapeller on 26/11/15.
 */
public interface ApiTask<T> {
    Result<T> await();
    Result<T> await(long timeoutMillis);
    ApiTask<T> addCallback(ApiCallback<T> callback);
    ApiTask<T> onResult(ResultCallback<T> callback);
    ApiTask<T> onError(ErrorCallback callback);
    void cancel();

}
