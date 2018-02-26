package com.ntg.user.mvpsample.add_task.model;

import io.reactivex.Observable;

/**
 * @author Sara Elmoghazy
 */
public class AddTaskRepository implements AddTaskDataSource {

    private static AddTaskRepository INSTANCE = null;
    private final AddTaskDataSource remoteAddTaskRepo;

    private AddTaskRepository(AddTaskDataSource remoteAddTaskRepo) {
        this.remoteAddTaskRepo = remoteAddTaskRepo;
    }

    public static AddTaskRepository getInstance(AddTaskDataSource remoteAddTaskRepo) {
        if (INSTANCE == null) {
            INSTANCE = new AddTaskRepository(remoteAddTaskRepo);
        }

        return INSTANCE;
    }

    @Override
    public Observable<Task> saveTask(Task task) {
        return remoteAddTaskRepo.saveTask(task);
    }
}
