package com.ntg.user.mvpsample.task_details;

import com.ntg.user.mvpsample.base.BasePresenter;
import com.ntg.user.mvpsample.base.BaseView;
import com.ntg.user.mvpsample.data.Task;

/**
 * TaskDetailsContract
 */

public interface TaskDetailsContract {

    interface View extends BaseView<Presenter> {
        void showTaskDetails();
        void navigateToEditTextUI();
    }

    interface Presenter extends BasePresenter {}
}
