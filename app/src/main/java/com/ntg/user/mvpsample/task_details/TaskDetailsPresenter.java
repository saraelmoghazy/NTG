package com.ntg.user.mvpsample.task_details;

import com.ntg.user.mvpsample.base.BasePresenter;
import com.ntg.user.mvpsample.network.Task;

/**
 * @author Sara Elmoghazy
 */

public class TaskDetailsPresenter extends BasePresenter<TaskDetailsViewContract> {

    private Task task;

    public TaskDetailsPresenter(TaskDetailsViewContract view, Task task) {
        super(view);
        this.task = task;
    }

    @Override
    public void start() {
        super.start();

        getView().showTitle(task.getTitle());
        getView().showDescription(task.getDescription());
        if (task.getSubTasks() != null && task.getSubTasks().size() > 0)
            getView().showSubTasks(task.getSubTasks());
    }
}
