package com.ntg.user.mvpsample.add_tasks;

import com.ntg.user.mvpsample.data.Task;
import com.ntg.user.mvpsample.data.sourse.TasksDataSource;
import com.ntg.user.mvpsample.data.sourse.remote.ApiInterface;
import com.ntg.user.mvpsample.data.sourse.remote.RetrofitProvider;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by islam on 1/30/2018.
 */

public class AddTaskRepo implements TasksDataSource.SaveTask {

    private static AddTaskRepo INSTANCE = null;
    RetrofitProvider retrofitProvider;

    private AddTaskRepo(RetrofitProvider retrofitProvider){
        this.retrofitProvider = retrofitProvider;
    }

    public static AddTaskRepo getInstance(RetrofitProvider retrofitProvider) {
        if (INSTANCE == null) {
            INSTANCE = new AddTaskRepo(retrofitProvider);
        }
        return INSTANCE;
    }

    @Override
    public void saveTask(Task task, AddTaskCallback addTaskCallback) {
        ApiInterface addNewTask = retrofitProvider.getRetrofit().create(ApiInterface.class);
        addNewTask.saveTask(task)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Task>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Task task) {
                        addTaskCallback.onTaskAdded(task);
                    }

                    @Override
                    public void onError(Throwable e) {
                        addTaskCallback.onTaskAddedFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
