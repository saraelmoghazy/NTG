package com.ntg.user.mvpsample.task_details;

import com.ntg.user.mvpsample.base.BasePresenter;
import com.ntg.user.mvpsample.base.BaseFragment;

/**
 * TaskDetailsContract
 */

public interface TaskDetailsContract {

    abstract class View extends BaseFragment<Presenter> {
        public abstract void showTaskDetails();
        abstract void navigateToEditTextUI();
    }

    abstract class Presenter extends BasePresenter {}
}
