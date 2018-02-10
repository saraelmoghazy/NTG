package com.ntg.user.mvpsample.task;

import com.ntg.user.mvpsample.Utils;
import com.ntg.user.mvpsample.data.SubTask;
import com.ntg.user.mvpsample.data.Task;
import com.ntg.user.mvpsample.data.remote.ApiInterface;
import com.ntg.user.mvpsample.data.remote.BaseObserver;
import com.ntg.user.mvpsample.data.remote.DaggerNetComponent;
import com.ntg.user.mvpsample.data.remote.ErrorType;
import com.ntg.user.mvpsample.data.remote.RetrofitException;
import com.ntg.user.mvpsample.data.remote.RetrofitProvider;
import com.ntg.user.mvpsample.data.remote.TasksDataSource;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RemoteTaskRepo implements TasksDataSource {

    public static final String TAG = RemoteTaskRepo.class.getSimpleName();

    private static RemoteTaskRepo instance;
    @Inject
    RetrofitProvider retrofit;

    private RemoteTaskRepo() {

    }


    public static RemoteTaskRepo getInstance() {
        if (instance == null)
            instance = new RemoteTaskRepo();
        DaggerNetComponent.Initializer.buildComponent().inject(instance);
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
        ApiInterface apiInterface = retrofit.getRetrofit().create(ApiInterface.class);
        apiInterface.getSubTasks(id)
                .flatMapIterable(subTasks -> subTasks)
                .map(SubTask::getProgress)
                .toList()
                .subscribe(list -> percent[0] = Utils.percentage(list));
        return percent[0];
    }

    @Override
    public void getTasks(final LoadTasksCallback loadTasksCallback) {
        ApiInterface apiService = retrofit.getRetrofit().create(ApiInterface.class);
        apiService.getTasks()
                .flatMap(this::convert)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<List<Task>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(List<Task> tasks) {
                        loadTasksCallback.onTasksLoaded(tasks);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        RetrofitException retrofitException = null;
                        if (throwable instanceof RetrofitException) {
                            retrofitException = (RetrofitException) throwable;
                            switch (retrofitException.getErrorType()) {
                                case ErrorType.NETWORK: {
                                    loadTasksCallback.onTaskLoadedFail(retrofitException.getMessage());
                                    break;
                                }
                                case ErrorType.HTTP: {
                                    loadTasksCallback.onTaskLoadedFail(retrofitException.getMessage());
                                    break;
                                }
                                default: {
                                    loadTasksCallback.onTaskLoadedFail(retrofitException.getMessage());
                                }
                            }
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
