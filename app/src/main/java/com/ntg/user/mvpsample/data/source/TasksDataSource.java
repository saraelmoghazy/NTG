package com.ntg.user.mvpsample.data.source;

import com.ntg.user.mvpsample.base.BasePresenter;
import com.ntg.user.mvpsample.base.ErrorCallback;
import com.ntg.user.mvpsample.data.Task;
import com.ntg.user.mvpsample.data.source.remote.network.ErrorObserver;

import java.util.List;

/**
 * Created by ilias on 25/01/2018.
 */

public interface TasksDataSource {
    void setPresenter(BasePresenter basePresenter);

    void loadData(GetTasksCallBack tasksCallBack);

    void saveTask(Task task, SaveTaskCallBack saveTaskCallBack);

    void upDateTask(Task task, SaveTaskCallBack saveTaskCallBack);

    void deleteTask(Task task);

    interface GetTasksCallBack {

        void onTasksLoaded(List<Task> tasks);
    }

    interface SaveTaskCallBack {

        void onTaskSaved();

        void onTaskFailed(String errorMsg);
    }
}
