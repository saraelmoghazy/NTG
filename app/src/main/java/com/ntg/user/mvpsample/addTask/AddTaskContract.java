package com.ntg.user.mvpsample.addTask;

import com.ntg.user.mvpsample.data.Task;

/**
 * Created by devsaad on 1/29/2018.
 */

public interface AddTaskContract {
    interface View{
        void showSuccessDataMsg();
        void showTasks();
    }
    interface Presenter{
        void addTask(Task task);
    }
}
