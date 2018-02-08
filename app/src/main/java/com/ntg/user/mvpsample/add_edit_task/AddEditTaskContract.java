package com.ntg.user.mvpsample.add_edit_task;

import com.ntg.user.mvpsample.base.BasePresenter;
import com.ntg.user.mvpsample.base.BaseFragment;
import com.ntg.user.mvpsample.data.Task;

/**
 * AddEditTaskContract contains View and presenter interfaces of AddTask module
 */

public interface AddEditTaskContract {

    abstract class View extends BaseFragment<Presenter> {
        public abstract void showTasks();

        public abstract void showTaskDetail(Task task);

        public abstract void showSaveTaskSuccessMsg();

        public abstract void showSaveTaskFailedMsg();
    }

    abstract class Presenter extends BasePresenter {
        abstract void saveTask(Task task);

        abstract void editTask(Task task);
    }
}
