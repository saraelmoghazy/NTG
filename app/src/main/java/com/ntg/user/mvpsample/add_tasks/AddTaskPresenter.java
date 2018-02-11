package com.ntg.user.mvpsample.add_tasks;

import com.ntg.user.mvpsample.data.Task;
import com.ntg.user.mvpsample.data.sourse.TasksDataSource;

import javax.inject.Inject;

/**
 * @author islam
 */

public class AddTaskPresenter implements IAddTaskPresenter{
    @Inject
    AddTaskRepo repository;
    private final IAddTaskView iAddTaskView;

    public AddTaskPresenter(IAddTaskView iAddTaskView){
        this.iAddTaskView = iAddTaskView;
        DaggerAddTaskComponent.Initializer.buildComponent().inject(this);
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
