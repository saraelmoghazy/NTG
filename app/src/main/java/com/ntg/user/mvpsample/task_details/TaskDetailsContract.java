package com.ntg.user.mvpsample.task_details;

import com.ntg.user.mvpsample.base.BasePresenter;
import com.ntg.user.mvpsample.base.BaseView;

/**
 * TaskDetailsContract
 */

public interface TaskDetailsContract {

    interface View extends BaseView<Presenter> {
        void showTaskDetails();
    }

    interface Presenter extends BasePresenter {}
}
