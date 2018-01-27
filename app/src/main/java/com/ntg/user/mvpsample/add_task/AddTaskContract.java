package com.ntg.user.mvpsample.add_task;

import com.ntg.user.mvpsample.BasePresenter;
import com.ntg.user.mvpsample.BaseView;
import com.ntg.user.mvpsample.data.Task;

/**
 * Created by ilias on 25/01/2018.
 */

public interface AddTaskContract {
    interface View extends BaseView<Presenter>{
        void showTasks();
        void showSaveTaskMsg();

    }

    interface Presenter extends BasePresenter{
        void saveTask(Task task);
    }
}
