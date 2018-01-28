package com.ntg.user.mvpsample.data.source;

import android.content.Context;

import com.ntg.user.mvpsample.data.Task;
import com.ntg.user.mvpsample.data.source.remote.TasksRemoteDataSource;

/**
 * Created by ilias on 25/01/2018.
 */

public class TasksRepository implements TasksDataSource {
    //    public static TasksRepository INSTANCE = null;
    private TasksDataSource tasksRemoteDataSource;
    private TasksDataSource tasksLocalDataSource;
    private Context context;

    public TasksRepository(Context context) {
        this.tasksRemoteDataSource = new TasksRemoteDataSource(context);
//        this.tasksLocalDataSource = new TasksLocalDataSource();
    }

    //    public static TasksRepository newInstance(Context context) {
//        if (INSTANCE == null){
//            INSTANCE = new TasksRepository(context, TasksRemoteDataSource.getINSTANCE(), TasksLocalDataSource.getINSTANCE());
//        }
//        return INSTANCE;
//    }
    @Override
    public void loadRemoteData(GetTasksCallBack getTasksCallBack) {
        tasksRemoteDataSource.loadRemoteData(getTasksCallBack);
    }

    @Override
    public Task saveTask(Task task) {
        return tasksRemoteDataSource.saveTask(task);
    }
}
