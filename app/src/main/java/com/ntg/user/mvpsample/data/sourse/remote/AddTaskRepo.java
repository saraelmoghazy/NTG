package com.ntg.user.mvpsample.data.sourse.remote;

import android.util.Log;

import com.ntg.user.mvpsample.data.Task;
import com.ntg.user.mvpsample.data.sourse.TasksDataSource;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by islam on 1/30/2018.
 */

public class AddTaskRepo implements TasksDataSource.SaveTask {

    private static AddTaskRepo INSTANCE = null;


    public static AddTaskRepo getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AddTaskRepo();
        }
        return INSTANCE;
    }
    @Override
    public void saveTask(Task task, AddTaskCallback addTaskCallback) {
        ApiInterface addNewTask = ApiClient.getClient().create(ApiInterface.class);
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
