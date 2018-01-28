package com.ntg.user.mvpsample.tasks;

import com.ntg.user.mvpsample.BasePresenter;
import com.ntg.user.mvpsample.BaseView;
import com.ntg.user.mvpsample.data.Task;

import java.util.List;

/**
 * Created by ilias on 25/01/2018.
 */

public interface TasksContract {

    interface View extends BaseView<Presenter> {
        void showTasks(List<Task> tasks);

        void showNoTasks();

        void showAddNewTaskUI();

        void showTaskDetailsUI();

        void showTaskAsComplete();

        void showTaskAsActive();
    }

    interface Presenter extends BasePresenter {
        void getTasks();

        void addTask();

        void updateTaskStatusAsCompleted(Task task);
    }
}
