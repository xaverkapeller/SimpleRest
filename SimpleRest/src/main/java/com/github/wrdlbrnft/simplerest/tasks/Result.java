package com.github.wrdlbrnft.simplerest.tasks;

import com.github.wrdlbrnft.simplerest.util.StatusCode;

/**
 * Created with Android Studio<br>
 * User: Xaver<br>
 * Date: 20/11/2016
 */
public interface Result<T> {
    @StatusCode
    int getStatusCode();
    T getData();
}
