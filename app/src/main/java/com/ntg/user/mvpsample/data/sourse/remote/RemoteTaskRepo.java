package com.ntg.user.mvpsample.data.sourse.remote;

import android.util.Log;

import com.ntg.user.mvpsample.data.Task;
import com.ntg.user.mvpsample.data.sourse.TasksDataSource;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.schedulers.IoScheduler;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteTaskRepo implements TasksDataSource{

    private static RemoteTaskRepo instance;

    private RemoteTaskRepo(){
    }

    public static RemoteTaskRepo getInstance() {
        if (instance == null)
            instance = new RemoteTaskRepo();
        return instance;
    }


    @Override
    public void getTasks(final LoadTasksCallback loadTasksCallback) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        apiService.getTasks()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Task>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<Task> tasks) {
                loadTasksCallback.onTasksLoaded(tasks);

            }

            @Override
            public void onError(Throwable e) {
                Log.i("erroe" , e.getMessage());
                loadTasksCallback.onTaskLoadedFail(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });

    }
}
