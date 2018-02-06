package com.ntg.user.mvpsample.task_details;

import com.ntg.user.mvpsample.BasePresenter;
import com.ntg.user.mvpsample.BaseView;
import com.ntg.user.mvpsample.remote.SubTask;
import com.ntg.user.mvpsample.remote.Task;
/**
 * Created by GM7 on 2/4/2018.
 */

public interface TaskDetailsContract {
    interface View extends BaseView<Presenter>{
        void showNewSubTaskInList(SubTask subTask);
        void showSavingSubTaskError();
        void showSavingSubTaskSuccess();
    
    }
    
    interface Presenter extends BasePresenter{
        void saveSubTask(String taskId, SubTask subTask);
    }
}
