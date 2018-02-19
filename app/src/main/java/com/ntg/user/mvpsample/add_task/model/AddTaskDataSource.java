package com.ntg.user.mvpsample.add_task.model;

import com.ntg.user.mvpsample.network.Task;

import io.reactivex.Observable;

/**
 * Created by Sara Elmoghazy on 11/02/2018.
 */

public interface AddTaskDataSource {

    Observable<Task> saveTask(Task task);
}
