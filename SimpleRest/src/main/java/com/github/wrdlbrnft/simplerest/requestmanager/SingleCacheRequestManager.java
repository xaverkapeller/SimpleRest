package com.github.wrdlbrnft.simplerest.requestmanager;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicReference;

import com.github.wrdlbrnft.simplerest.callbacks.ApiCallback;
import com.github.wrdlbrnft.simplerest.callbacks.OnModificationCallback;
import com.github.wrdlbrnft.simplerest.taskrunners.ApiTaskRunner;
import com.github.wrdlbrnft.simplerest.tasks.ApiTask;
import com.github.wrdlbrnft.simplerest.tasks.Result;

/**
 * Created with Android Studio<br>
 * User: Xaver<br>
 * Date: 20/11/2016
 */
public class SingleCacheRequestManager<T> {

    private final AtomicReference<ApiTask<T>> mTaskReference = new AtomicReference<>(null);
    private final AtomicReference<T> mCache = new AtomicReference<>(null);

    private final ApiTaskRunner mTaskRunner;
    private final OnModificationCallback<T> mModificationCallback;

    public SingleCacheRequestManager(ApiTaskRunner taskRunner, OnModificationCallback<T> modificationCallback) {
        mTaskRunner = taskRunner;
        mModificationCallback = modificationCallback;
    }

    public ApiTask<T> perform(Callable<Result<T>> callable) {
        final T cachedItem = mCache.get();
        if (cachedItem != null) {
            return mTaskRunner.cacheTask(cachedItem);
        }

        final ApiTask<T> runningTask = mTaskReference.get();
        if (runningTask != null) {
            return runningTask;
        }

        final ApiTask<T> task = mTaskRunner.queueTask(callable, new ApiCallback<T>() {

            @Override
            public void onResult(int statusCode, T item) {
                mTaskReference.set(null);
                if (item != null) {
                    mCache.set(item);
                    if (mModificationCallback != null) {
                        mModificationCallback.onModification(item);
                    }
                }
            }

            @Override
            public void onError() {
                mTaskReference.set(null);
            }
        });
        mTaskReference.set(task);
        return task;
    }

    public void invalidateCache() {
        mCache.set(null);
    }
}
