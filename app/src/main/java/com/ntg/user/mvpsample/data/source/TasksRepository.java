package com.ntg.user.mvpsample.data.source;

import android.annotation.SuppressLint;
import android.content.Context;

import com.ntg.user.mvpsample.base.BasePresenter;
import com.ntg.user.mvpsample.base.ErrorCallback;
import com.ntg.user.mvpsample.data.Task;
import com.ntg.user.mvpsample.data.source.remote.TasksRemoteDataSource;

/**
 * Created by ilias on 25/01/2018.
 */

public class TasksRepository implements TasksDataSource {

    private static TasksRepository INSTANCE = null;
    private TasksDataSource tasksRemoteDataSource;
    private BasePresenter basePresenter;

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
    public void setPresenter(BasePresenter basePresenter) {
        this.basePresenter = basePresenter;
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
    public void upDateTask(Task task, SaveTaskCallBack saveTaskCallBack) {
        tasksRemoteDataSource.upDateTask(task, saveTaskCallBack);
    }

    @Override
    public void deleteTask(Task task) {
        tasksRemoteDataSource.deleteTask(task);
    }
}
