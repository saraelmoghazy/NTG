package com.ntg.user.mvpsample.task;

import com.ntg.user.mvpsample.BaseView;
import com.ntg.user.mvpsample.data.Task;
import java.util.List;

/**
 * @author islam task view
 */

public interface ITaskView extends BaseView {
    void showTasks(List<Task> tasks);
    void showAddNewTask();
}
