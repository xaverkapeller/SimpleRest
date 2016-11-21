package com.github.wrdlbrnft.simplerest.requestmanager;

import android.support.v4.util.ArrayMap;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.Callable;

import com.github.wrdlbrnft.simplerest.callbacks.ApiCallback;
import com.github.wrdlbrnft.simplerest.callbacks.OnModificationCallback;
import com.github.wrdlbrnft.simplerest.caches.Cache;
import com.github.wrdlbrnft.simplerest.taskrunners.ApiTaskRunner;
import com.github.wrdlbrnft.simplerest.tasks.ApiTask;
import com.github.wrdlbrnft.simplerest.tasks.Result;

/**
 * Created with Android Studio<br>
 * User: Xaver<br>
 * Date: 20/11/2016
 */
public class MultiCacheRequestManager<K, T> {

    private final Map<K, ApiTask<T>> mTaskMap = Collections.synchronizedMap(new ArrayMap<K, ApiTask<T>>());
    private final Cache<K, T> mCache;

    private final ApiTaskRunner mTaskRunner;
    private final OnModificationCallback<T> mModificationCallback;

    public MultiCacheRequestManager(ApiTaskRunner taskRunner, Cache<K, T> cache, OnModificationCallback<T> modificationCallback) {
        mTaskRunner = taskRunner;
        mCache = cache;
        mModificationCallback = modificationCallback;
    }

    public ApiTask<T> perform(final K id, Callable<Result<T>> callable) {
        final T cachedItem = mCache.get(id);
        if (cachedItem != null) {
            return mTaskRunner.cacheTask(cachedItem);
        }

        final ApiTask<T> runningTask = mTaskMap.get(id);
        if (runningTask != null) {
            return runningTask;
        }

        final ApiTask<T> task = mTaskRunner.queueTask(callable, new ApiCallback<T>() {

            @Override
            public void onResult(int statusCode, T item) {
                mTaskMap.remove(id);
                if (item != null) {
                    mCache.put(id, item);
                    if (mModificationCallback != null) {
                        mModificationCallback.onModification(item);
                    }
                }
            }

            @Override
            public void onError() {
                mTaskMap.remove(id);
            }
        });
        mTaskMap.put(id, task);
        return task;
    }

    public void invalidateCache(K id) {
        mCache.evict(id);
    }

    public void clearCache() {
        mCache.clear();
    }
}
