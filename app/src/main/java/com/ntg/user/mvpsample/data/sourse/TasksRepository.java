package com.ntg.user.mvpsample.data.sourse;

import android.annotation.SuppressLint;
import com.ntg.user.mvpsample.data.Task;
import java.util.List;

import static android.support.v4.util.Preconditions.checkNotNull;

/**
 * @author islam
 */

public class TasksRepository implements TasksDataSource {

    private static TasksRepository INSTANCE = null;
    private final TasksDataSource tasksRemoteDataSource;

    private TasksRepository(TasksDataSource mTasksRemoteDataSource) {
        this.tasksRemoteDataSource = mTasksRemoteDataSource;
    }

    public static TasksRepository getInstance(TasksDataSource tasksRemoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new TasksRepository(tasksRemoteDataSource);
        }
        return INSTANCE;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void getTasks(LoadTasksCallback loadTasksCallback) {
            checkNotNull(loadTasksCallback);
            getTasksFromRemoteDataSource(loadTasksCallback);
    }

    /**
     *
     * @param loadTasksCallback
     */
    private void getTasksFromRemoteDataSource(final LoadTasksCallback loadTasksCallback){
        tasksRemoteDataSource.getTasks(new LoadTasksCallback() {
            @Override
            public void onTasksLoaded(List<Task> tasks) {
                loadTasksCallback.onTasksLoaded(tasks);
            }

            @Override
            public void onTaskLoadedFail(String errMesg) {
                loadTasksCallback.onTaskLoadedFail(errMesg);
            }
        });
    }
}
