package com.ntg.user.mvpsample.add_subtask.model;

import com.ntg.user.mvpsample.network.SubTask;
import com.ntg.user.mvpsample.network.Task;

import io.reactivex.Observable;

/**
 * Created by Sara Elmoghazy on 11/02/2018.
 */

public interface AddSubTaskDataSource {

    Observable<SubTask> saveSubTask(String id, SubTask subTask);
}
