package com.ntg.user.mvpsample.data.sourse.remote;

import android.util.Log;

import com.ntg.user.mvpsample.ApiError;
import com.ntg.user.mvpsample.ErrorType;
import com.ntg.user.mvpsample.RetrofitError;
import com.ntg.user.mvpsample.Utils;
import com.ntg.user.mvpsample.data.SubTask;
import com.ntg.user.mvpsample.data.Task;
import com.ntg.user.mvpsample.data.sourse.TasksDataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

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

    private io.reactivex.Observable<List<Task>> convert(List<Task> tasks){
        for (Task task: tasks){
            task.setProgress(getSubTasksProgress(task.getId()));
        }
        return io.reactivex.Observable.just(tasks);
    }

    @Override
    public int getSubTasksProgress(String id) {
        final Integer[] percent = {0};
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.getSubTasks(id)
                .flatMapIterable(subTasks -> subTasks)
                .map(SubTask::getProgress)
                .toList()
                .subscribe(list -> percent[0] =  Utils.percentage(list));
        return percent[0];
    }

    @Override
    public void getTasks(final LoadTasksCallback loadTasksCallback) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        apiService.getTasks()
                .flatMap(this::convert)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Task>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<Task> tasks) {
                if (tasks.size() != 0){
                    loadTasksCallback.onTasksLoaded(tasks);
                }else {

                }
            }

            @Override
            public void onError(Throwable e) {
                loadTasksCallback.onTaskLoadedFail(e.getMessage());
                if(e instanceof ApiError){
                    switch (((ApiError) e).getType()){
                        case ErrorType.HTTP:
                            Log.e("sss" , ApiError.getMessage());
                    }
                }
                ApiError error =  RetrofitError.covertError(e);
                switch (error.getType()){
                    case ErrorType.HTTP:
                        Log.e("sss" , error.getMessage());
                }
            }

            @Override
            public void onComplete() {

            }
        });

    }
}
