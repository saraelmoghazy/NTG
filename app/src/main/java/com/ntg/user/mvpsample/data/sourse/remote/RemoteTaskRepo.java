package com.ntg.user.mvpsample.data.sourse.remote;

import com.ntg.user.mvpsample.ApiError;
import com.ntg.user.mvpsample.ErrorType;
import com.ntg.user.mvpsample.Utils;
import com.ntg.user.mvpsample.data.SubTask;
import com.ntg.user.mvpsample.data.Task;
import com.ntg.user.mvpsample.data.sourse.TasksDataSource;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RemoteTaskRepo implements TasksDataSource{

    private static RemoteTaskRepo instance;
    @Inject
    RetrofitProvider retrofitProvider;

    private RemoteTaskRepo(){
    }

    public static RemoteTaskRepo getInstance() {
        if (instance == null)
            instance = new RemoteTaskRepo();
        DaggerNetComponent.Initializer.buildComponent().inject(instance);
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
        ApiInterface apiInterface = retrofitProvider.getRetrofit().create(ApiInterface.class);
        apiInterface.getSubTasks(id)
                .flatMapIterable(subTasks -> subTasks)
                .map(SubTask::getProgress)
                .toList()
                .subscribe(list -> percent[0] =  Utils.percentage(list));
        return percent[0];
    }

    @Override
    public void getTasks(final LoadTasksCallback loadTasksCallback) {
        ApiInterface apiService = retrofitProvider.getRetrofit().create(ApiInterface.class);
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
                if(e instanceof ApiError){
                    ApiError error = (ApiError) e;
                    switch (error.getType()){
                        case ErrorType.HTTP:
                            loadTasksCallback.onTaskLoadedFail(e.getMessage());
                    }
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
