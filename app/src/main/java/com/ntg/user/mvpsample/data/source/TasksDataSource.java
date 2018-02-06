package com.ntg.user.mvpsample.data.source;

import com.ntg.user.mvpsample.base.ErrorCallback;
import com.ntg.user.mvpsample.data.Task;

import java.util.List;

/**
 * Created by ilias on 25/01/2018.
 */

public interface TasksDataSource {

    void loadData(GetTasksCallBack tasksCallBack,ErrorCallback errorCallback);

    void saveTask(Task task, SaveTaskCallBack saveTaskCallBack);

    void upDateTask(Task task, SaveTaskCallBack saveTaskCallBack);

    void deleteTask(Task task);

    boolean getTaskProgress(String taskId);

    interface GetTasksCallBack {

        void onTasksLoaded(List<Task> tasks);
    }

    interface SaveTaskCallBack {

        void onTaskSaved();

        void onTaskFailed(String errorMsg);
    }
}
