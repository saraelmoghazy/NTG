package com.ntg.user.mvpsample.data.sourse;

import com.ntg.user.mvpsample.data.Task;

import java.util.List;

/**
 * Created by islam on 1/27/2018.
 */

public interface TasksDataSource {

    interface LoadTasksCallback{
        void onTasksLoaded(List<Task> tasks);
        void onTaskLoadedFail();
    }

    void getTasks(LoadTasksCallback loadTasksCallback);
}
