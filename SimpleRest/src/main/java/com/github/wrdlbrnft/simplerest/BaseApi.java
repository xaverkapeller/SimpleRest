package com.github.wrdlbrnft.simplerest;

import com.github.wrdlbrnft.simplerest.callbacks.ApiCallback;
import com.github.wrdlbrnft.simplerest.taskrunners.ApiTaskRunner;
import com.github.wrdlbrnft.simplerest.tasks.ApiTask;
import com.github.wrdlbrnft.simplerest.tasks.Result;

import java.util.concurrent.Callable;

/**
 * Created by kapeller on 26/11/15.
 */
public abstract class BaseApi implements Api {

    private final ApiTaskRunner mRunner;

    protected BaseApi(ApiTaskRunner runner) {
        mRunner = runner;
    }

    protected <T> ApiTask<T> queueTask(Callable<Result<T>> callable) {
        return mRunner.queueTask(callable);
    }

    protected <T> ApiTask<T> queueTask(Callable<Result<T>> callable, ApiCallback<T> internalCallback) {
        return mRunner.queueTask(callable, internalCallback);
    }

    protected <T> ApiTask<T> cacheTask(T result) {
        return mRunner.cacheTask(result);
    }

    public ApiTaskRunner getRunner() {
        return mRunner;
    }

    public static class ResultImpl<T> implements Result<T> {

        private final int mStatusCode;
        private final T mItem;

        public ResultImpl(int statusCode, T item) {
            mStatusCode = statusCode;
            mItem = item;
        }

        @Override
        public int getStatusCode() {
            return mStatusCode;
        }

        @Override
        public T getData() {
            return mItem;
        }
    }

    public static class EmptyResultImpl<T> implements Result<T> {

        private final int mStatusCode;

        public EmptyResultImpl(int statusCode) {
            mStatusCode = statusCode;
        }

        @Override
        public int getStatusCode() {
            return mStatusCode;
        }

        @Override
        public T getData() {
            return null;
        }
    }
}
