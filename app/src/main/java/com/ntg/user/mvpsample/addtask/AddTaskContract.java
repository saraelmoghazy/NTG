package com.ntg.user.mvpsample.addtask;

import com.ntg.user.mvpsample.BasePresenter;
import com.ntg.user.mvpsample.BaseView;
import com.ntg.user.mvpsample.data.Task;

/**
 * Created by mohamed yassin on 1/28/2018.
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
