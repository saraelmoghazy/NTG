package com.ntg.user.mvpsample.add_subtask.model;

import io.reactivex.Observable;

/**
 * @author Sara Elmoghazy
 */

public class AddSubTaskRepository implements AddSubTaskDataSource {

    private static AddSubTaskRepository INSTANCE = null;
    private final AddSubTaskDataSource addSubTaskDataSource;

    private AddSubTaskRepository(AddSubTaskDataSource addSubTaskDataSource) {
        this.addSubTaskDataSource = addSubTaskDataSource;
    }

    public static AddSubTaskRepository getInstance(AddSubTaskDataSource remoteAddSubTaskRepo) {
        if (INSTANCE == null) {
            INSTANCE = new AddSubTaskRepository(remoteAddSubTaskRepo);
        }

        return INSTANCE;
    }

    @Override
    public Observable<SubTask> saveSubTask(int id, SubTasks subTasks) {

        return addSubTaskDataSource.saveSubTask(id, subTasks);
    }
}
