package com.github.wrdlbrnft.simplerest.requestmanager;

import android.support.v4.util.ArrayMap;

import com.github.wrdlbrnft.simplerest.BaseApi;
import com.github.wrdlbrnft.simplerest.caches.Cache;
import com.github.wrdlbrnft.simplerest.callbacks.ApiCallback;
import com.github.wrdlbrnft.simplerest.callbacks.ErrorCallback;
import com.github.wrdlbrnft.simplerest.callbacks.OnModificationCallback;
import com.github.wrdlbrnft.simplerest.callbacks.ResultCallback;
import com.github.wrdlbrnft.simplerest.taskrunners.ApiTaskRunner;
import com.github.wrdlbrnft.simplerest.tasks.ApiTask;
import com.github.wrdlbrnft.simplerest.tasks.Result;
import com.github.wrdlbrnft.simplerest.util.StatusCode;
import com.github.wrdlbrnft.simplerest.util.StatusCodes;
import com.github.wrdlbrnft.simpletasks.utils.TaskUtils;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created with Android Studio<br>
 * User: Xaver<br>
 * Date: 20/11/2016
 */
public class MultiCacheRequestManager<K, T> {

    private final Map<K, ApiTask<T>> mTaskMap = new ArrayMap<>();

    private final ApiTaskRunner mTaskRunner;
    private final Cache<K, T> mCache;
    private final OnModificationCallback<T> mModificationCallback;

    public MultiCacheRequestManager(ApiTaskRunner taskRunner, Cache<K, T> cache, OnModificationCallback<T> modificationCallback) {
        mTaskRunner = taskRunner;
        mCache = cache;
        mModificationCallback = modificationCallback;
    }

    public ApiTask<T> perform(final K key, Callable<Result<T>> callable) {
        synchronized (mTaskMap) {
            final T cachedValue = mCache.get(key);
            if (cachedValue != null) {
                return new CacheApiTaskImpl<>(cachedValue);
            }

            final ApiTask<T> cachedTask = mTaskMap.get(key);
            if (cachedTask != null) {
                return cachedTask;
            }

            final ApiTask<T> task = mTaskRunner.queueTask(callable)
                    .onResult(new ResultCallback<T>() {
                        @Override
                        public void onResult(@StatusCode int statusCode, T result) {
                            synchronized (mTaskMap) {
                                mTaskMap.remove(key);
                                if (result == null) {
                                    return;
                                }
                                mCache.put(key, result);

                                if (mModificationCallback != null) {
                                    mModificationCallback.onModification(result);
                                }
                            }
                        }
                    })
                    .onError(new ErrorCallback() {
                        @Override
                        public void onError(Throwable throwable) {
                            synchronized (mTaskMap) {
                                mTaskMap.remove(key);
                            }
                        }
                    });
            mTaskMap.put(key, task);
            return task;
        }
    }

    public void invalidateCache(K id) {
        synchronized (mTaskMap) {
            mCache.evict(id);
            mTaskMap.remove(id);
        }
    }

    public void clearCache() {
        synchronized (mTaskMap) {
            mCache.clear();
            mTaskMap.clear();
        }
    }

    private static class CacheApiTaskImpl<T> implements ApiTask<T> {

        private final T mResult;

        private CacheApiTaskImpl(T result) {
            mResult = result;
        }

        @Override
        public Result<T> await() {
            return new BaseApi.ResultImpl<>(StatusCodes.Success.OK, mResult);
        }

        @Override
        public Result<T> await(long timeoutMillis) {
            return new BaseApi.ResultImpl<>(StatusCodes.Success.OK, mResult);
        }

        @Override
        public ApiTask<T> addCallback(final ApiCallback<T> callback) {
            TaskUtils.MAIN_HANDLER.post(new Runnable() {
                @Override
                public void run() {
                    callback.onResult(StatusCodes.Success.OK, mResult);
                }
            });
            return this;
        }

        @Override
        public ApiTask<T> onResult(final ResultCallback<T> callback) {
            TaskUtils.MAIN_HANDLER.post(new Runnable() {
                @Override
                public void run() {
                    callback.onResult(StatusCodes.Success.OK, mResult);
                }
            });
            return this;
        }

        @Override
        public ApiTask<T> onError(ErrorCallback callback) {
            return this;
        }

        @Override
        public void cancel() {

        }
    }
}
