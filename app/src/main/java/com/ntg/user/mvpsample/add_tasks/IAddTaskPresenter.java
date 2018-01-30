package com.ntg.user.mvpsample.add_tasks;

import com.ntg.user.mvpsample.BasePresenter;
import com.ntg.user.mvpsample.data.Task;

/**
 * Created by islam on 1/29/2018.
 */

public interface IAddTaskPresenter extends BasePresenter {

    void saveTask(Task task);
}
