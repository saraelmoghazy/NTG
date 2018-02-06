package com.ntg.user.mvpsample.task_details;

import android.util.Log;

import com.ntg.user.mvpsample.TasksDataSource;
import com.ntg.user.mvpsample.remote.SubTask;
/**
 * Created by GM7 on 2/4/2018.
 */

public class TaskDetailsPresenter implements TaskDetailsContract.Presenter {
    private String TAG = TaskDetailsContract.Presenter.class.getSimpleName();
    TaskDetailsContract.View view;
    TasksDataSource tasksDataSource;
    
    public TaskDetailsPresenter(TaskDetailsContract.View view, TasksDataSource tasksDataSource) {
        this.view = view;
        this.tasksDataSource = tasksDataSource;
        view.setPresenter(this);
    }
    
    
    @Override
    public void saveSubTask(String taskId, SubTask subTask) {
        tasksDataSource.saveSubTask(taskId, subTask, new TasksDataSource.SaveSubTaskCallBack() {
            @Override
            public void onSubTaskSaved() {
                Log.e(TAG , "onSubTask");
                view.showSavingSubTaskSuccess();
                view.showNewSubTaskInList(subTask);
            }
            
            @Override
            public void onSubTaskFailed(String errorMessage){
                Log.e(TAG , "subTaskError");
            }
        });
    }
}
