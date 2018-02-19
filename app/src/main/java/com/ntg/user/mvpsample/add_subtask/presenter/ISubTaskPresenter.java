package com.ntg.user.mvpsample.add_subtask.presenter;

import com.ntg.user.mvpsample.network.SubTask;

/**
 * @author islam
 */

public interface ISubTaskPresenter extends BasePresenter {
    void saveSubTask(String id ,  SubTask subTasks);
}
