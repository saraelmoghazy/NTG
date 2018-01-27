package com.ntg.user.mvpsample.add_task;

import com.ntg.user.mvpsample.data.Task;
import com.ntg.user.mvpsample.data.source.TasksDataSource;

/**
 * Created by ilias on 25/01/2018.
 */

public class AddTaskPresenter implements AddTaskContract.Presenter {
    private AddTaskContract.View addTaskView;
    private TasksDataSource tasksRepository;

    public AddTaskPresenter(AddTaskContract.View addTaskView, TasksDataSource tasksRepository) {
        this.addTaskView = addTaskView;
        this.tasksRepository = tasksRepository;
        addTaskView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void saveTask(Task task) {
        tasksRepository.saveTask(task);
        addTaskView.showSaveTaskMsg();
        addTaskView.showTasks();
    }
}
