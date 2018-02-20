package com.ntg.user.mvpsample.add_task.model;

import com.ntg.user.mvpsample.base.BaseObservable;
import com.ntg.user.mvpsample.network.Task;
import com.ntg.user.mvpsample.network.remote.APIEndPoint;
import com.ntg.user.mvpsample.network.remote.RetrofitProvider;

import io.reactivex.Observable;

/**
 * @author Sara Elmoghazy
 */

public class RemoteAddTaskRepo extends BaseObservable implements AddTaskDataSource {

    private static RemoteAddTaskRepo INSTANCE = null;
    RetrofitProvider retrofitProvider;


    private RemoteAddTaskRepo(RetrofitProvider retrofitProvider) {
        this.retrofitProvider = retrofitProvider;
    }

    public static RemoteAddTaskRepo getInstance(RetrofitProvider retrofitProvider) {
        if (INSTANCE == null)
            INSTANCE = new RemoteAddTaskRepo(retrofitProvider);
        return INSTANCE;
    }

    @Override
    public Observable<Task> saveTask(Task task) {
        APIEndPoint addNewTask = retrofitProvider.getRetrofit().create(APIEndPoint.class);
        return getObservable(addNewTask.saveTask(task));
    }
}
