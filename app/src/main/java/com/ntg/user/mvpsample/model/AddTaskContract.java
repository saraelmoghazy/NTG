package com.ntg.user.mvpsample.model;

import com.ntg.user.mvpsample.PresenterLayer.InterfacePresenter;
import com.ntg.user.mvpsample.PresenterLayer.ViewInterface;

/**
 * AddTaskContract contains View and presenter interfaces of AddTask module
 */

public interface AddTaskContract {

    interface View extends ViewInterface<Presenter> {
        void showTasks();

        void showSaveTaskSuccessMsg();

        void showSaveTaskFailedMsg();
    }

    interface Presenter extends InterfacePresenter {
        void saveTask(Task task);
    }
}
