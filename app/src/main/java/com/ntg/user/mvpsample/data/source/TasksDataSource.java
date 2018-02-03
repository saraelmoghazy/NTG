package com.ntg.user.mvpsample.data.source;

import android.support.annotation.NonNull;

import com.ntg.user.mvpsample.data.Task;

import java.util.List;

/**
 * Created by mohamed yassin on 1/28/2018.
 */

public interface TasksDataSource {


    interface GetTaskCallback {
        void onTasksLoaded(List<Task> tasks);
        void onDataNotAvailable(String msgError);
    }

   interface SaveTaskCallBack{
       void onTaskSaved();
       void onTaskFailed(String msgError);
   }
    void loadTask(GetTaskCallback getTaskCallback);
    void saveTask(Task task, SaveTaskCallBack saveTask);


}
