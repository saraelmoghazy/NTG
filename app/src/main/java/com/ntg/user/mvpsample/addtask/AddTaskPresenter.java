package com.ntg.user.mvpsample.addtask;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.ntg.user.mvpsample.data.Task;
import com.ntg.user.mvpsample.data.source.TasksDataSource;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * Created by mohamed yassin on 1/28/2018.
 */

public class AddTaskPresenter implements AddTaskContract.Presenter {
    private AddTaskContract.View addTaskView;
    private TasksDataSource tasksRepository;

    AddTaskPresenter(AddTaskContract.View addTaskView, TasksDataSource tasksRepository) {
        this.addTaskView = addTaskView;
        this.tasksRepository = tasksRepository;
        addTaskView.setPresenter(this);
    }


    @Override
    public void start() {

    }

    @Override
    public void saveTask(Task task) {
       tasksRepository.saveTask(task, new TasksDataSource.SaveTaskCallBack() {
           @Override
           public void onTaskSaved() {
               addTaskView.showSaveTaskSuccessMsg();
           }

           @Override
           public void onTaskFailed(String msgError) {
               addTaskView.showSaveTaskFailedMsg();
           }
       });
       addTaskView.showTasks();
    }
}
