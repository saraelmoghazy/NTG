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

    public static final String TAG = RemoteGetTasksRepo.class.getSimpleName();
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
            task.setProgress(getSubTasksProgress(task.getId()));
        }
        return io.reactivex.Observable.just(tasks);
    }

    @Override
    public int getSubTasksProgress(String id) {
        final Integer[] percent = {0};
        APIEndPoint APIEndPoint = retrofitProvider.getRetrofit().create(APIEndPoint.class);
        APIEndPoint.getSubTasks(id)
                .flatMapIterable(subTasks -> subTasks)
                .map(SubTask::getProgress)
                .toList()
                .subscribe(list -> percent[0] = Utils.percentage(list));
        return percent[0];
    }

    @Override
    public Observable<List<Task>> getTasks() {
        APIEndPoint apiService = retrofitProvider.getRetrofit().create(APIEndPoint.class);
        return getObservable(apiService.getTasks()
                .flatMap(this::convert));
    }
}
