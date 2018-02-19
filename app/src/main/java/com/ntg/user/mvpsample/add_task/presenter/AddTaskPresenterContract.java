package com.ntg.user.mvpsample.add_task.presenter;

import com.ntg.user.mvpsample.network.Task;

/**
 * @author islam
 */

public interface AddTaskPresenterContract extends BasePresenter {
    void saveTask(Task task);
}
