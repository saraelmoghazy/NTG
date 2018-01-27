package com.ntg.user.mvpsample.tasks;

import com.ntg.user.mvpsample.data.Task;
import com.ntg.user.mvpsample.data.source.TasksDataSource;

/**
 * Created by ilias on 25/01/2018.
 */

public class TasksPresenter implements TasksContract.Presenter {

    private TasksContract.View tasksView;
    private TasksDataSource tasksRepository;

    public TasksPresenter(TasksContract.View tasksView, TasksDataSource tasksRepository) {
        this.tasksView = tasksView;
        this.tasksRepository = tasksRepository;
        tasksView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getTasks() {

    }

    @Override
    public void addTask() {
        tasksView.showAddNewTaskUI();
    }
}
