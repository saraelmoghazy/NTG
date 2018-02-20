package com.ntg.user.mvpsample.tasks.model;

import com.ntg.user.mvpsample.base.BaseObservable;
import com.ntg.user.mvpsample.Utils;
import com.ntg.user.mvpsample.network.SubTask;
import com.ntg.user.mvpsample.network.Task;
import com.ntg.user.mvpsample.network.remote.APIEndPoint;
import com.ntg.user.mvpsample.network.remote.RetrofitProvider;

import java.util.List;

import io.reactivex.Observable;

public class RemoteGetTasksRepo extends BaseObservable implements GetTasksDataSource {

    private static RemoteGetTasksRepo instance;
    RetrofitProvider retrofitProvider;

    private RemoteGetTasksRepo(RetrofitProvider retrofitProvider) {
        this.retrofitProvider = retrofitProvider;
    }

    public static RemoteGetTasksRepo getInstance(RetrofitProvider retrofitProvider) {
        if (instance == null)
            instance = new RemoteGetTasksRepo(retrofitProvider);

        return instance;
    }


    private io.reactivex.Observable<List<Task>> convert(List<Task> tasks) {
        for (Task task : tasks) {
            if (task.getSubTasks() != null && task.getSubTasks().size() > 0) {
                getTaskProgress(task.getId()).subscribe(progress -> task.setProgress(progress));
            }
        }

        return Observable.just(tasks);
    }

    @Override
    public Observable<Integer> getTaskProgress(int taskId) {
        APIEndPoint APIEndPoint = retrofitProvider.getRetrofit().create(APIEndPoint.class);
        return APIEndPoint.getSubTasks(taskId)
                .flatMapIterable(subTasks -> subTasks)
                .map(subTask -> subTask.getProgress())
                .toList()
                .map(progress -> Utils.getAverage(progress)).toObservable();
    }

    @Override
    public Observable<List<Task>> getTasks() {
        APIEndPoint apiService = retrofitProvider.getRetrofit().create(APIEndPoint.class);

        return getObservable(apiService.getTasks().flatMap(this::convert));
    }
}
