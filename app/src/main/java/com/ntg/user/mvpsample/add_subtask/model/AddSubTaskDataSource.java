package com.ntg.user.mvpsample.add_subtask.model;

import io.reactivex.Observable;

/**
 * @author Sara Elmoghazy
 */

public interface AddSubTaskDataSource {

    Observable<SubTask> saveSubTask(int id, SubTasks subTasks);
}
