package com.ntg.user.mvpsample.add_tasks;

import com.ntg.user.mvpsample.BasePresenter;
import com.ntg.user.mvpsample.data.Task;

/**
 * @author islam
 */

public interface IAddTaskPresenter extends BasePresenter {
    void saveTask(Task task);
}
