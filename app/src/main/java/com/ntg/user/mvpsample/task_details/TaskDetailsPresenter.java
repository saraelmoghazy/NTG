package com.ntg.user.mvpsample.task_details;

import com.ntg.user.mvpsample.base.BasePresenter;

/**
 * TaskDetailsPresenter control of showing task details
 */

public class TaskDetailsPresenter extends TaskDetailsContract.Presenter {

    private TaskDetailsContract.View taskDetailsView;

    public TaskDetailsPresenter(TaskDetailsContract.View taskDetailsView) {
        this.taskDetailsView = taskDetailsView;
        super.setFragment(taskDetailsView);
        taskDetailsView.setPresenter(this);
    }

    @Override
    public void start() {
        taskDetailsView.showTaskDetails();
    }
}
