package com.ntg.user.mvpsample.add_subtask;

import com.ntg.user.mvpsample.BasePresenter;
import com.ntg.user.mvpsample.data.SubTask;

import java.util.List;

/**
 * @author islam
 */

public interface ISubTaskPresenter extends BasePresenter {
    void saveSubTask(String id ,  SubTask subTasks);
}
