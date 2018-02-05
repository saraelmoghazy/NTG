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
        interface AddTaskCallback{
            void onTaskAdded(Task task);
            void onTaskAddedFail(String errMessage);
        }
        void saveTask(Task task , AddTaskCallback addTaskCallback);
    }


    interface SaveSubTask{
        interface AddSubTaskCallback{
            void onSubTaskAdded(SubTask subTask);
            void inSubTaskAddedFail(String errMessage);
        }
        void saveSubTask(String id , SubTask subTasks , AddSubTaskCallback addSubTaskCallback);
    }
}
