package com.ntg.user.mvpsample.data.source;

import com.ntg.user.mvpsample.data.Task;

import java.util.List;

/**
 * Created by ilias on 25/01/2018.
 */

public interface TasksDataSource {

    void loadData(GetTasksCallBack tasksCallBack);

    void saveTask(Task task, SaveTaskCallBack saveTaskCallBack);

    void upDateTask(Task task);

    interface GetTasksCallBack {

        void onTasksLoaded(List<Task> tasks);

        void onTasksFailed(String errorMsg);
    }

    interface SaveTaskCallBack {

        void onTaskSaved();

        void onTaskFailed(String errorMsg);
    }
}
