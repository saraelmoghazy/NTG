package com.ntg.user.mvpsample;



import com.ntg.user.mvpsample.remote.Task;

import java.util.List;

/**
 * Created by GM7 on 1/29/2018.
 */

public interface TasksContract {
    interface View{
        void showTasks(List<Task> tasks);
        void showSaveTaskSuccessMsg();
        void showSaveTaskFailedMsg();
        void setPresenter(Presenter presenter);
    }

    interface Presenter{
        void getTask();
        void saveTask(Task task);
    }
}
