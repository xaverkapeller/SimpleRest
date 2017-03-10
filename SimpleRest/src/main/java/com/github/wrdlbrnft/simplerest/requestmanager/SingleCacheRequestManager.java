package com.github.wrdlbrnft.simplerest.requestmanager;

import com.github.wrdlbrnft.simplerest.caches.MapCache;
import com.github.wrdlbrnft.simplerest.callbacks.OnModificationCallback;
import com.github.wrdlbrnft.simplerest.taskrunners.ApiTaskRunner;
import com.github.wrdlbrnft.simplerest.tasks.ApiTask;
import com.github.wrdlbrnft.simplerest.tasks.Result;

import java.util.concurrent.Callable;

/**
 * Created with Android Studio<br>
 * User: Xaver<br>
 * Date: 20/11/2016
 */
public class SingleCacheRequestManager<T> {

    private static final String DEFAULT_KEY = "DEFAULT";

    private final MultiCacheRequestManager<String, T> mRequestManager;

    public SingleCacheRequestManager(ApiTaskRunner taskRunner, OnModificationCallback<T> modificationCallback) {
        mRequestManager = new MultiCacheRequestManager<>(taskRunner, new MapCache<String, T>(), modificationCallback);
    }

    public ApiTask<T> perform(Callable<Result<T>> callable) {
        return mRequestManager.perform(DEFAULT_KEY, callable);
    }

    public void invalidateCache() {
        mRequestManager.clearCache();
    }
}
