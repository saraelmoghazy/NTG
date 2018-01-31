package com.ntg.user.mvpsample.tasks;

import com.ntg.user.mvpsample.base.BasePresenter;
import com.ntg.user.mvpsample.base.BaseView;
import com.ntg.user.mvpsample.data.Task;

import java.util.List;

/**
 * Created by ilias on 25/01/2018.
 */

public interface TasksContract {

    interface View extends BaseView<Presenter> {
        void showTasks(List<Task> tasks);

        void showNoTasks();

        void showNetworkError();

        void showAddNewTaskUI();

        void showTaskDetailsUI(Task task);
    }

    interface Presenter extends BasePresenter {
        void getTasks();

        void navigateToAddTaskUI();

        void updateTaskStatus(Task task);
    }
}