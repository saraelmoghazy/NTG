package com.ntg.user.mvpsample.show_task;

import com.ntg.user.mvpsample.model.taskdatasources.TasksDataSource;

/**
 * ShowTaskPresenter control of showing task details
 */

public class ShowTaskPresenter implements ShowTaskInterFace.Presenter {

    private TasksDataSource taskRepository;
    private ShowTaskInterFace.View taskDetailsView;

    ShowTaskPresenter(ShowTaskInterFace.View taskDetailsView, TasksDataSource taskRepository) {
        this.taskDetailsView = taskDetailsView;
        this.taskRepository = taskRepository;
        taskDetailsView.setPresenter(this);
    }

    @Override
    public void start() {
        taskDetailsView.showTaskDetails();
    }
}
