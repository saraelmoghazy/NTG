package com.ntg.user.mvpsample.add_task.model;

import com.ntg.user.mvpsample.model.StoryTask;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author Sara Elmoghazy
 */

public class AddTaskRepository implements AddTaskDataSource {

    private static AddTaskRepository INSTANCE = null;
    private final AddTaskDataSource addTaskDataSource;

    private AddTaskRepository(AddTaskDataSource addTaskDataSource) {
        this.addTaskDataSource = addTaskDataSource;
    }

    public static AddTaskRepository getInstance(AddTaskDataSource remoteAddTaskRepo) {
        if (INSTANCE == null) {
            INSTANCE = new AddTaskRepository(remoteAddTaskRepo);
        }

        return INSTANCE;
    }

    @Override
    public Observable<Void> saveTasks(int storyId, List<StoryTask> storyTasks) {

        return addTaskDataSource.saveTasks(storyId, storyTasks);
    }
}
