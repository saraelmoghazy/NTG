package com.ntg.user.mvpsample.tasks;

import com.ntg.user.mvpsample.PresenterLayer.InterfacePresenter;
import com.ntg.user.mvpsample.PresenterLayer.ViewInterface;
import com.ntg.user.mvpsample.model.Task;

import java.util.List;

/**
 * Created by ilias on 25/01/2018.
 */

public interface TasksContract {

    interface View extends ViewInterface<Presenter> {
        void showTasks(List<Task> tasks);

        void showNoTasks();

        void showNetworkError();

        void showAddNewTaskUI();

        void showUpdateTaskUI(Task task);

        void showTaskDetailsUI(Task task);
    }

    interface Presenter extends InterfacePresenter {
        void getTasks();

        void navigateToAddTaskUI();

        void updateTaskStatus(Task task);

        void updateTask(Task task);

        void deleteTask(Task task);
    }
}
