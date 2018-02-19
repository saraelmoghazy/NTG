package com.ntg.user.mvpsample.tasks.model;

import com.ntg.user.mvpsample.network.Task;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author islam
 */

public class GetTasksRepository implements GetTasksDataSource {

    private static GetTasksRepository INSTANCE = null;
    private final GetTasksDataSource tasksRemoteDataSource;

    private GetTasksRepository(GetTasksDataSource mTasksRemoteDataSource) {
        this.tasksRemoteDataSource = mTasksRemoteDataSource;
    }

    public static GetTasksRepository getInstance(GetTasksDataSource tasksRemoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new GetTasksRepository(tasksRemoteDataSource);
        }
        return INSTANCE;
    }

    @Override
    public int getSubTasksProgress(String id) {
        return tasksRemoteDataSource.getSubTasksProgress(id);
    }

    @Override
    public Observable<List<Task>> getTasks() {
        return tasksRemoteDataSource.getTasks();
    }
}
