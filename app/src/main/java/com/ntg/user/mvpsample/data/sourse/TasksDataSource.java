package com.ntg.user.mvpsample.data.sourse;

import android.service.autofill.SaveCallback;

import com.ntg.user.mvpsample.data.Task;

import java.util.List;

/**
 * Created by islam on 1/27/2018.
 */

public interface TasksDataSource {

    interface LoadTasksCallback{
        void onTasksLoaded(List<Task> tasks);
        void onTaskLoadedFail(String errMesg);
    }

    void getTasks(LoadTasksCallback loadTasksCallback);
    
    interface SaveTaskCallback{
        void onSavTaskSuccess(Task task);
        void onSaveTaskFail(String errMessage);
    }
    
    void saveTask(SaveTaskCallback saveTaskCallback);
}
