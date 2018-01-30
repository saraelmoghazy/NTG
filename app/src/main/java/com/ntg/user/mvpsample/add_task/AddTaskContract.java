package com.ntg.user.mvpsample.add_task;

import com.ntg.user.mvpsample.base.BasePresenter;
import com.ntg.user.mvpsample.base.BaseView;
import com.ntg.user.mvpsample.data.Task;

/**
 * AddTaskContract contains View and presenter interfaces of AddTask module
 */

public interface AddTaskContract {

    interface View extends BaseView<Presenter> {
        void showTasks();

        void showSaveTaskSuccessMsg();

        void showSaveTaskFailedMsg();
    }

    interface Presenter extends BasePresenter {
        void saveTask(Task task);
    }
}
