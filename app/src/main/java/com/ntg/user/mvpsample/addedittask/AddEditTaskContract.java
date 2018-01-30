package com.ntg.user.mvpsample.addedittask;

import com.ntg.user.mvpsample.BasePresenter;

/**
 * Created by mohamed yassin on 1/28/2018.
 */

public interface AddEditTaskContract {
    interface View extends BaseView<Presenter> {

        void showEmptyTaskError();

        void showTasksList();
    }

    interface Presenter extends BasePresenter {
        void saveTask(String title, String description);
    }
}
