

package com.ntg.user.mvpsample.data.source;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


import com.ntg.user.mvpsample.data.Task;
import com.ntg.user.mvpsample.data.source.remote.TaskRemoteDataSource;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;


public class TasksRepository  implements TasksDataSource{
    private static TasksRepository INSTANCE = null;
    private TasksDataSource tasksRemoteDataSource;

    private TasksRepository(TaskRemoteDataSource tasksRemoteDataSource) {
        this.tasksRemoteDataSource = tasksRemoteDataSource;
    }

    public static TasksRepository getInstance(TaskRemoteDataSource tasksRemoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new TasksRepository(tasksRemoteDataSource);
        }

        return INSTANCE;
    }


    @Override
    public void loadTask(GetTaskCallback getTaskCallback) {
        tasksRemoteDataSource.loadTask(getTaskCallback);

    }

    @Override
    public void saveTask(Task task, SaveTaskCallBack saveTaskCallBack) {
        tasksRemoteDataSource.saveTask(task,saveTaskCallBack);

    }

    @Override
    public boolean getTasksProgress(String taskId) {
        return tasksRemoteDataSource.getTasksProgress(taskId);
    }


}

