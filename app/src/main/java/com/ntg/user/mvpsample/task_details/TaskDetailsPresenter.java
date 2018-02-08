package com.ntg.user.mvpsample.task_details;

import com.ntg.user.mvpsample.data.Task;
import com.ntg.user.mvpsample.data.source.TasksDataSource;

/**
 * TaskDetailsPresenter control of showing task details
 */

public class TaskDetailsPresenter extends TaskDetailsContract.Presenter {

    private TasksDataSource taskRepository;
    private TaskDetailsContract.View taskDetailsView;

    public TaskDetailsPresenter(TaskDetailsContract.View taskDetailsView, TasksDataSource taskRepository) {
        this.taskDetailsView = taskDetailsView;
        this.taskRepository = taskRepository;
        super.setFragment(taskDetailsView);
        taskDetailsView.setPresenter(this);
    }

    @Override
    public void start() {
        taskDetailsView.showTaskDetails();
    }
}
