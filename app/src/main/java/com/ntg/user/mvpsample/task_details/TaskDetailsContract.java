package com.ntg.user.mvpsample.task_details;

import com.ntg.user.mvpsample.BasePresenter;
import com.ntg.user.mvpsample.BaseView;

/**
 * Created by ilias on 25/01/2018.
 */

public interface TaskDetailsContract {

    interface View extends BaseView<Presenter>{
void showTaskDetails();
    }

    interface Presenter extends BasePresenter{
        void getTask(String taskId);
    }
}
