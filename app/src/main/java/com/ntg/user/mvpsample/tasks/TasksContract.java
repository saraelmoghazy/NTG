package com.ntg.user.mvpsample.tasks;

import com.ntg.user.mvpsample.data.Task;

import java.util.List;

/**
 * This interface includes tow interfaces view and presenter.
 * the view interface contain three function that will be called from
 * task adapter.
 * the second interface contains function start that will be called from
 * class task presenter.
 */

public interface TasksContract {


    interface View{
        void showTask(List<Task> tasks);
        void setPresenter(Presenter presenter);
        void showNoTasks();
    }

    interface Presenter{
        void start();
    }
}
