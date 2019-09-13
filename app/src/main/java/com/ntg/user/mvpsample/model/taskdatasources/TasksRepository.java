package com.ntg.user.mvpsample.model.taskdatasources;

import com.ntg.user.mvpsample.model.Task;

/**
 * Created by ilias on 25/01/2018.
 */

public class TasksRepository implements TasksDataSource {

    private static TasksRepository INSTANCE = null;
    private TasksDataSource tasksRemoteDataSource;

    private TasksRepository(TasksRemoteDataSource tasksRemoteDataSource) {
        this.tasksRemoteDataSource = tasksRemoteDataSource;
    }

    public static TasksRepository getInstance(TasksRemoteDataSource tasksRemoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new TasksRepository(tasksRemoteDataSource);
        }

        return INSTANCE;
    }

    @Override
    public void loadData(GetTasksCallBack getTasksCallBack) {
        tasksRemoteDataSource.loadData(getTasksCallBack);
    }

    @Override
    public void saveTask(Task task, SaveTaskCallBack saveTaskCallBack) {
        tasksRemoteDataSource.saveTask(task, saveTaskCallBack);
    }


    @Override
    public boolean getTaskProgress(String taskId) {
        return tasksRemoteDataSource.getTaskProgress(taskId);
    }
}