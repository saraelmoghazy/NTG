package com.ntg.user.mvpsample.add_subtask.presenter;

import com.ntg.user.mvpsample.network.SubTask;

/**
 * @author islam
 */

public interface SubTaskPresenterContract {
    void saveSubTask(String id, SubTask subTasks);
}
