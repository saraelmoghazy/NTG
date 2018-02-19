package com.ntg.user.mvpsample.task_details;

import com.ntg.user.mvpsample.base.BasePresenter;
import com.ntg.user.mvpsample.network.Task;

/**
 * @author islam
 */

public class TaskDetailsPresenter extends BasePresenter<TaskDetailsViewContract> {

    private Task task;

    public TaskDetailsPresenter(TaskDetailsViewContract view, Task task) {
        super(view);
        this.task = task;
    }

    @Override
    public void start() {
        getView().showTitle(task.getTitle());
        getView().showDescription(task.getDescription());
        getView().showSubTasks(task.getSubTasks());
    }
}
