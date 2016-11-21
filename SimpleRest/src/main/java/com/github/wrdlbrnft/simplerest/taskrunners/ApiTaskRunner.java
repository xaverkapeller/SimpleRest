package com.github.wrdlbrnft.simplerest.taskrunners;

import com.github.wrdlbrnft.simplerest.BaseApi;
import com.github.wrdlbrnft.simplerest.callbacks.ApiCallback;
import com.github.wrdlbrnft.simplerest.tasks.ApiTask;
import com.github.wrdlbrnft.simplerest.tasks.Result;
import com.github.wrdlbrnft.simplerest.util.StatusCodes;
import com.github.wrdlbrnft.simpletasks.runners.TaskRunner;
import com.github.wrdlbrnft.simpletasks.tasks.ErrorCallback;
import com.github.wrdlbrnft.simpletasks.tasks.ResultCallback;
import com.github.wrdlbrnft.simpletasks.tasks.Task;

import java.util.concurrent.Callable;

/**
 * Created with Android Studio<br>
 * User: Xaver<br>
 * Date: 20/11/2016
 */
public class ApiTaskRunner {

    private final TaskRunner mRunner;

    public ApiTaskRunner(TaskRunner runner) {
        mRunner = runner;
    }

    public <T> ApiTask<T> queueTask(Callable<Result<T>> callable) {
        final Task<Result<T>> task = mRunner.queue(callable);
        return new ApiTaskImpl<>(task);
    }

    public <T> ApiTask<T> queueTask(Callable<Result<T>> callable, final ApiCallback<T> internalCallback) {
        final Task<Result<T>> task = mRunner.queue(callable);
        task.onResult(new ResultCallback<Result<T>>() {
            @Override
            public void onResult(Result<T> result) {
                internalCallback.onResult(result.getStatusCode(), result.getData());
            }
        });
        task.onError(new ErrorCallback() {
            @Override
            public void onError(Throwable throwable) {
                internalCallback.onError();
            }
        });
        return new ApiTaskImpl<>(task);
    }

    public <T> ApiTask<T> cacheTask(final T result) {
        return queueTask(new Callable<Result<T>>() {
            @Override
            public Result<T> call() throws Exception {
                return new BaseApi.ResultImpl<T>(StatusCodes.Success.OK, result);
            }
        });
    }

    public void start() {
        mRunner.start();
    }

    public void stop() {
        mRunner.stop();
    }

    public boolean isRunning() {
        return mRunner.getState() == TaskRunner.STATE_RUNNING;
    }
}
