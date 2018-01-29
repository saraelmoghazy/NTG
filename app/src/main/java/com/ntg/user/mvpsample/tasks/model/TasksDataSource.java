package com.ntg.user.mvpsample.tasks.model;

import com.ntg.user.mvpsample.data.Task;

import java.util.List;

/**
 * Created by devsaad on 1/29/2018.
 */

public interface TasksDataSource {

    interface GetTasksCallBack {
        void onTasksSuccess(List<Task> tasks);

        void onTasksFailed(String errMessage);
    }

    void getTasks(GetTasksCallBack getTasksCallBack);
}
