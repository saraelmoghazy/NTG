package com.ntg.user.mvpsample.add_edit_task;

import com.ntg.user.mvpsample.base.BasePresenter;
import com.ntg.user.mvpsample.base.BaseView;
import com.ntg.user.mvpsample.data.Task;

/**
 * AddEditTaskContract contains View and presenter interfaces of AddTask module
 */

public interface AddEditTaskContract {

    interface View extends BaseView<Presenter> {
        void showTasks();

        void showTaskDetail(Task task);

        void showSaveTaskSuccessMsg();

        void showSaveTaskFailedMsg();
    }

    interface Presenter extends BasePresenter {
        void saveTask(Task task);

        void editTask(Task task);
    }
}
