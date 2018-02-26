package com.ntg.user.mvpsample.tasks.model;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author Sara Elmoghazy
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
    public Observable<Integer> getTaskProgress(List<SubTask> subTasks) {
        return tasksRemoteDataSource.getTaskProgress(subTasks);
    }

    @Override
    public Observable<List<Task>> getTasks() {
        return tasksRemoteDataSource.getTasks();
    }
}
