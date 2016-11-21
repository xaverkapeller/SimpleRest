package com.github.wrdlbrnft.simplerest.taskrunners;

import com.github.wrdlbrnft.simplerest.BaseApi;
import com.github.wrdlbrnft.simplerest.callbacks.ApiCallback;
import com.github.wrdlbrnft.simplerest.callbacks.ErrorCallback;
import com.github.wrdlbrnft.simplerest.callbacks.ResultCallback;
import com.github.wrdlbrnft.simplerest.exceptions.ApiCallFailedException;
import com.github.wrdlbrnft.simplerest.tasks.ApiTask;
import com.github.wrdlbrnft.simplerest.tasks.Result;
import com.github.wrdlbrnft.simplerest.util.StatusCode;
import com.github.wrdlbrnft.simpletasks.utils.TaskUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created with Android Studio<br>
 * User: Xaver<br>
 * Date: 20/11/2016
 */
public class StubApiTask<T> implements ApiTask<T> {

    private volatile Result<T> mResult;

    private final List<ApiCallback<T>> mCallbacks = new ArrayList<>();

    private AtomicBoolean mWaiting = new AtomicBoolean(false);

    @Override
    public Result<T> await() {
        try {
            mWaiting.set(true);
            mWaiting.wait();
            return mResult;
        } catch (InterruptedException e) {
            throw new ApiCallFailedException("Could not finish task", e);
        }
    }

    @Override
    public Result<T> await(long timeoutMillis) {
        try {
            mWaiting.set(true);
            mWaiting.wait(timeoutMillis);
            return mResult;
        } catch (InterruptedException e) {
            throw new ApiCallFailedException("Could not finish task", e);
        }
    }

    @Override
    public ApiTask<T> addCallback(ApiCallback<T> callback) {
        mCallbacks.add(callback);
        return this;
    }

    @Override
    public ApiTask<T> onResult(final ResultCallback<T> callback) {
        mCallbacks.add(new ApiCallback<T>() {
            @Override
            public void onResult(@StatusCode int statusCode, T result) {
                callback.onResult(statusCode, result);
            }

            @Override
            public void onError() {

            }
        });
        return this;
    }

    @Override
    public ApiTask<T> onError(final ErrorCallback callback) {
        mCallbacks.add(new ApiCallback<T>() {
            @Override
            public void onResult(@StatusCode int statusCode, T result) {

            }

            @Override
            public void onError() {
                callback.onError();
            }
        });
        return this;
    }

    @Override
    public void cancel() {
        notifyError();
    }

    public void notifyResult(@StatusCode final int statusCode, final T data) {
        mResult = new BaseApi.ResultImpl<>(statusCode, data);
        if (mWaiting.get()) {
            mWaiting.notifyAll();
        }
        TaskUtils.MAIN_HANDLER.post(new Runnable() {
            @Override
            public void run() {
                for (ApiCallback<T> callback : mCallbacks) {
                    callback.onResult(statusCode, data);
                }
            }
        });
    }

    public void notifyError() {
        mResult = null;
        if (mWaiting.get()) {
            mWaiting.notifyAll();
        }
        TaskUtils.MAIN_HANDLER.post(new Runnable() {
            @Override
            public void run() {
                for (ApiCallback<T> callback : mCallbacks) {
                    callback.onError();
                }
            }
        });
    }
}
