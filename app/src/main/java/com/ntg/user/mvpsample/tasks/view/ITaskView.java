package com.ntg.user.mvpsample.tasks.view;

import com.ntg.user.mvpsample.base.BaseView;
import com.ntg.user.mvpsample.network.Task;

import java.util.List;

/**
 * @author islam task view
 */

public interface ITaskView extends BaseView {

    void showTasks(List<Task> tasks);

    void showAddNewTask();
}
