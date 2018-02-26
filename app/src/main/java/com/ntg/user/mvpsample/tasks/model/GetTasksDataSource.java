package com.ntg.user.mvpsample.tasks.model;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author Sara Elmoghazy
 */

public interface GetTasksDataSource {

    Observable<Integer> getTaskProgress(List<SubTask> subTasks);

    Observable<List<Task>> getTasks();
}
