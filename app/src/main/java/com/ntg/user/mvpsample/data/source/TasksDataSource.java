package com.ntg.user.mvpsample.data.source;

import com.ntg.user.mvpsample.data.Task;

import java.util.List;

/**
 * Created by ilias on 25/01/2018.
 */

public interface TasksDataSource {
    void loadRemoteData(GetTasksCallBack tasksCallBack);

    Task saveTask(Task task);

    interface GetTasksCallBack {
        void onTasksLoaded(List<Task> tasks);

        void onTasksFailed(String errorMsg);
    }
}
