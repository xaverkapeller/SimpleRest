package com.github.wrdlbrnft.simplerest.taskrunners;

import com.github.wrdlbrnft.simplerest.tasks.ApiTask;
import com.github.wrdlbrnft.simplerest.tasks.Result;
import com.github.wrdlbrnft.simpletasks.runners.TaskRunner;
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
