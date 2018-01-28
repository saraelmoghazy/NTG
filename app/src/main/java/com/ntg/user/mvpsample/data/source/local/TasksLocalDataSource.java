package com.ntg.user.mvpsample.data.source.local;

import com.ntg.user.mvpsample.data.Task;
import com.ntg.user.mvpsample.data.source.TasksDataSource;
import com.ntg.user.mvpsample.data.source.remote.TasksRemoteDataSource;

/**
 * Created by ilias on 25/01/2018.
 */

public class TasksLocalDataSource implements TasksDataSource {
    static TasksLocalDataSource INSTANCE;

    private TasksLocalDataSource() {
    }

    public static TasksDataSource getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new TasksLocalDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void loadRemoteData(GetTasksCallBack getTasksCallBack) {

    }

    @Override
    public Task saveTask(Task task) {
        return null;
    }
}
