package com.ntg.user.mvpsample.tasks.model;

import com.ntg.user.mvpsample.network.Task;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author Sara Elmoghazy
 */

public interface GetTasksDataSource {

    Observable<Integer> getTaskProgress(int id);

    Observable<List<Task>> getTasks();
}
