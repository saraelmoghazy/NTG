package com.ntg.user.mvpsample.tasks;

import com.ntg.user.mvpsample.data.Task;

import java.util.List;

/**
 * Created by devsaad on 1/30/2018.
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
