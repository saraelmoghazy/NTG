package com.ntg.user.mvpsample.task;

import com.ntg.user.mvpsample.BaseView;
import com.ntg.user.mvpsample.data.Task;

import java.util.List;

/**
 * Created by islam on 1/27/2018.
 */

public interface ITaskView extends BaseView {
    void showTasks(List<Task> tasks);
    void showAddNewTask();
}
