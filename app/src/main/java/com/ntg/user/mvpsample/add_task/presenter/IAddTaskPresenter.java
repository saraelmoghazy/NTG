package com.ntg.user.mvpsample.add_task.presenter;

import com.ntg.user.mvpsample.network.Task;

/**
 * @author islam
 */

public interface IAddTaskPresenter extends BasePresenter {
    void saveTask(Task task);
}
