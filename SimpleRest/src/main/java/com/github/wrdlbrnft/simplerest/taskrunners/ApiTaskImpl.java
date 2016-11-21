package com.github.wrdlbrnft.simplerest.taskrunners;

import com.github.wrdlbrnft.simplerest.callbacks.ApiCallback;
import com.github.wrdlbrnft.simplerest.callbacks.ErrorCallback;
import com.github.wrdlbrnft.simplerest.callbacks.ResultCallback;
import com.github.wrdlbrnft.simplerest.tasks.ApiTask;
import com.github.wrdlbrnft.simplerest.tasks.Result;
import com.github.wrdlbrnft.simpletasks.tasks.Task;

/**
 * Created with Android Studio<br>
 * User: Xaver<br>
 * Date: 20/11/2016
 */
class ApiTaskImpl<T> implements ApiTask<T> {

    private final Task<Result<T>> mTask;

    ApiTaskImpl(Task<Result<T>> task) {
        mTask = task;
    }

    @Override
    public Result<T> await() {
        return mTask.await();
    }

    @Override
    public Result<T> await(long timeoutMillis) {
        return mTask.await(timeoutMillis);
    }

    @Override
    public ApiTask<T> addCallback(final ApiCallback<T> callback) {
        mTask.onResult(new com.github.wrdlbrnft.simpletasks.tasks.ResultCallback<Result<T>>() {
            @Override
            public void onResult(Result<T> result) {
                callback.onResult(result.getStatusCode(), result.getData());
            }
        });
        mTask.onError(new com.github.wrdlbrnft.simpletasks.tasks.ErrorCallback() {
            @Override
            public void onError(Throwable throwable) {
                callback.onError();
            }
        });
        return this;
    }

    @Override
    public ApiTask<T> onResult(final ResultCallback<T> callback) {
        mTask.onResult(new com.github.wrdlbrnft.simpletasks.tasks.ResultCallback<Result<T>>() {
            @Override
            public void onResult(Result<T> result) {
                callback.onResult(result.getStatusCode(), result.getData());
            }
        });
        return this;
    }

    @Override
    public ApiTask<T> onError(final ErrorCallback callback) {
        mTask.onError(new com.github.wrdlbrnft.simpletasks.tasks.ErrorCallback() {
            @Override
            public void onError(Throwable throwable) {
                callback.onError();
            }
        });
        return this;
    }

    @Override
    public void cancel() {
        mTask.cancel();
    }
}
