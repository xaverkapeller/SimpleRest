package com.github.wrdlbrnft.simplerest;

import com.github.wrdlbrnft.simplerest.callbacks.ApiCallback;
import com.github.wrdlbrnft.simplerest.taskrunners.ApiTaskRunner;
import com.github.wrdlbrnft.simplerest.tasks.ApiTask;
import com.github.wrdlbrnft.simplerest.tasks.Result;

import java.util.concurrent.Callable;

/**
 * Created with Android Studio<br>
 * User: Xaver<br>
 * Date: 20/11/2016
 */
public abstract class BaseApi implements Api {

    private ApiTaskRunner mRunner;

    protected BaseApi(ApiTaskRunner runner) {
        mRunner = runner;
    }
    
    protected BaseApi() {
        this(null);
    }
    
    protected void setApiTaskRunner(ApiTaskRunner runner) {
        mRunner = runner;
    }

    protected <T> ApiTask<T> queueTask(Callable<Result<T>> callable) {
        return mRunner.queueTask(callable);
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
