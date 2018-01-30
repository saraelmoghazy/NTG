package com.ntg.user.mvpsample.data.sourse;

import com.ntg.user.mvpsample.data.Task;
import java.util.List;

/**
 * @author islam
 */

public interface TasksDataSource {

    interface LoadTasksCallback{
        void onTasksLoaded(List<Task> tasks);
        void onTaskLoadedFail(String errMesg);
    }

    void getTasks(LoadTasksCallback loadTasksCallback);
    Task saveTask(Task task);
}
