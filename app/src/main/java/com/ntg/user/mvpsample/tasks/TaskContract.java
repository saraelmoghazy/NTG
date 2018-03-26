package com.ntg.user.mvpsample.tasks;

import com.ntg.user.mvpsample.BasePresenter;
import com.ntg.user.mvpsample.BaseView;
import com.ntg.user.mvpsample.data.Task;

import java.util.List;

/**
 * Created by mohamed yassin on 1/29/2018.
 */

public class TaskContract {
    public interface View extends BaseView<TaskPresenter> {

        void showTasks(List<Task> taskList);
        void showNoTasks();
        void showMessageError(String msg);


    }


    public interface Presenter extends BasePresenter {
        void getTasks();


    }
}
