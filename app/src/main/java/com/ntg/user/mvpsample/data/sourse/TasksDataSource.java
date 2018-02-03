package com.ntg.user.mvpsample.data.sourse;

import com.ntg.user.mvpsample.data.SubTask;
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

    int getSubTasksProgress(String id);

    void getTasks(LoadTasksCallback loadTasksCallback);

    interface SaveTask{
        void saveTask(Task task);
    }

    interface SaveSubTask{
        void saveSubTask(String id , SubTask subTasks);
    }
}
