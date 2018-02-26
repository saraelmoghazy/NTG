package com.ntg.user.mvpsample.add_task.model;

import io.reactivex.Observable;

/**
 * @author Sara Elmoghazy
 */

public interface AddTaskDataSource {

    Observable<Task> saveTask(Task task);
}
