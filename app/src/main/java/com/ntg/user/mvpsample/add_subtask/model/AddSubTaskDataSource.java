package com.ntg.user.mvpsample.add_subtask.model;

import com.ntg.user.mvpsample.network.SubTask;
import com.ntg.user.mvpsample.network.Task;

import io.reactivex.Observable;

/**
 * @author Sara Elmoghazy
 */

public interface AddSubTaskDataSource {

    Observable<SubTask> saveSubTask(int id, SubTask subTask);
}
