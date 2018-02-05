package com.ntg.user.mvpsample.add_tasks;

import android.util.Log;

import com.ntg.user.mvpsample.data.Task;
import com.ntg.user.mvpsample.data.sourse.TasksDataSource;
import com.ntg.user.mvpsample.data.sourse.remote.AddTaskRepo;

/**
 * @author islam
 */

public class AddTaskPresenter implements IAddTaskPresenter{

    private final AddTaskRepo repository;
    private final IAddTaskView iAddTaskView;

    public AddTaskPresenter(AddTaskRepo repository, IAddTaskView iAddTaskView){
        this.repository = repository;
        this.iAddTaskView = iAddTaskView;
    }

    @Override
    public void start(){
    }

    @Override
    public void saveTask(Task task) {
        repository.saveTask(task, new TasksDataSource.SaveTask.AddTaskCallback() {
            @Override
            public void onTaskAdded(Task task) {
                iAddTaskView.showAddSuccess("Task Added");
            }

            @Override
            public void onTaskAddedFail(String errMessage) {
                iAddTaskView.showAddFail("Task Added Fail");
            }
        });
    }

    @Override
    public void getTaskId() {

    }
}
