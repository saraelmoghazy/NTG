package com.ntg.user.mvpsample.task;

import com.ntg.user.mvpsample.data.sourse.TasksRepository;

/**
 * Created by islam on 1/27/2018.
 */

public class TaskPresenter implements ITaskPresenter {

    private final TasksRepository tasksRepository;

    private final ITaskView iTaskView;

    public TaskPresenter(TasksRepository tasksRepository, ITaskView iTaskView) {
        this.tasksRepository = tasksRepository;
        this.iTaskView = iTaskView;

        iTaskView.setPresenter(this);
    }

    @Override
    public void start() {
        loadTasks();
    }

    @Override
    public void loadTasks() {

    }

    @Override
    public void addNewTasks() {
        iTaskView.showAddNewTask();
    }
}
