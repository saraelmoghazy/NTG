package com.ntg.user.mvpsample.data.sourse;

import android.annotation.SuppressLint;
import android.content.Context;
import android.service.autofill.SaveCallback;

import com.ntg.user.mvpsample.data.Task;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.util.Preconditions.checkNotNull;

/**
 * Created by islam on 1/27/2018.
 */

public class TasksRepository implements TasksDataSource {

    private static TasksRepository INSTANCE = null;

    private final TasksDataSource tasksRemoteDataSource;

    private Context context;

    boolean cachedTasks = false;

    List<Task> taskList = new ArrayList<>();





    private TasksRepository(TasksDataSource mTasksRemoteDataSource) {
        this.tasksRemoteDataSource = mTasksRemoteDataSource;

    }

    public static TasksRepository getInstance(TasksDataSource tasksRemoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new TasksRepository(tasksRemoteDataSource);
        }
        return INSTANCE;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void getTasks(LoadTasksCallback loadTasksCallback) {
            checkNotNull(loadTasksCallback);
            getTasksFromRemoteDataSource(loadTasksCallback);

    }

    @Override
    public void saveTask(SaveTaskCallback saveTaskCallback) {
        saveTaskToRemoteDataSource(saveTaskCallback);
    }


    private void getTasksFromRemoteDataSource(final LoadTasksCallback loadTasksCallback){
        tasksRemoteDataSource.getTasks(new LoadTasksCallback() {
            @Override
            public void onTasksLoaded(List<Task> tasks) {
                loadTasksCallback.onTasksLoaded(tasks);
            }

            @Override
            public void onTaskLoadedFail(String errMesg) {
                loadTasksCallback.onTaskLoadedFail(errMesg);
            }
        });
    }

    private void saveTaskToRemoteDataSource(SaveTaskCallback saveTaskCallback){
        tasksRemoteDataSource.saveTask(new SaveTaskCallback() {
            @Override
            public void onSavTaskSuccess(Task task) {
                saveTaskCallback.onSavTaskSuccess(task);
            }

            @Override
            public void onSaveTaskFail(String errMessage) {
                saveTaskCallback.onSaveTaskFail(errMessage);
            }
        });
    }
}
