package com.ntg.user.mvpsample.tasks.model;

import com.ntg.user.mvpsample.network.SubTask;
import com.ntg.user.mvpsample.network.Task;

import java.util.List;

/**
 * @author islam
 */

public interface GetTasksDataSource {

    int getSubTasksProgress(String id);

    io.reactivex.Observable<List<Task>> getTasks();
}
