package com.github.wrdlbrnft.simplerest.util;

import android.support.annotation.NonNull;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by kapeller on 25/11/15.
 */
public class ExecutorHelper {

    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    private static final int KEEP_ALIVE = 1;

    @NonNull
    public static ThreadPoolExecutor createOptimalThreadPoolExecutor(final String threadNamePrefix) {
        final ThreadFactory factory = new ThreadFactory() {
            private final AtomicInteger mCount = new AtomicInteger(1);

            public Thread newThread(@NonNull Runnable runnable) {
                return new Thread(runnable, threadNamePrefix + " #" + mCount.getAndIncrement());
            }
        };

        final BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(128);
        return new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE, TimeUnit.SECONDS, workQueue, factory);
    }
}
