package com.ntg.user.mvpsample.tasks;

import com.ntg.user.mvpsample.base.BasePresenter;
import com.ntg.user.mvpsample.base.BaseFragment;
import com.ntg.user.mvpsample.data.Task;

import java.util.List;

/**
 * Created by ilias on 25/01/2018.
 */

public interface TasksContract {

    abstract class View extends BaseFragment<Presenter> {
        public abstract void showTasks(List<Task> tasks);

        abstract void showNoTasks();

        abstract void showLoadingIndicator();

        public abstract void showAddNewTaskUI();

        abstract void showTaskDetailsUI(Task task);
    }

    abstract class Presenter extends BasePresenter {
        abstract void getTasks();

        abstract void navigateToAddTaskUI();

        abstract void deleteTask(Task task);
    }
}
