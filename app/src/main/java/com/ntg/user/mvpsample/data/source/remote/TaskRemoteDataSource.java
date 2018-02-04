package com.ntg.user.mvpsample.data.source.remote;



import android.support.annotation.NonNull;

import com.ntg.user.mvpsample.MathUtil;
import com.ntg.user.mvpsample.data.Subtask;
import com.ntg.user.mvpsample.data.Task;
import com.ntg.user.mvpsample.data.source.TasksDataSource;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



/**
 * Created by mohamed yassin on 2/2/2018.
 */

public class TaskRemoteDataSource implements TasksDataSource {
    private static TaskRemoteDataSource INSTANCE;

    private TaskRemoteDataSource() {
    }

    public static TaskRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TaskRemoteDataSource();
        }

        return INSTANCE;
    }
    @Override
    public void loadTask(final GetTaskCallback tasksCallBack) {
        ApiService apiService =
                ApiClient.getClient().create(ApiService.class);

        apiService.getTasks()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Task>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Task> tasks) {
                        tasksCallBack.onTasksLoaded(tasks);
                    }

                    @Override
                    public void onError(Throwable e) {
                       tasksCallBack.onDataNotAvailable(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void saveTask(Task task, final SaveTaskCallBack saveTaskCallBack) {
        ApiService apiService =
                ApiClient.getClient().create(ApiService.class);
        apiService.saveTask(task)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Task>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Task task) {
                        saveTaskCallBack.onTaskSaved();

                    }

                    @Override
                    public void onError(Throwable e) {

                        saveTaskCallBack.onTaskFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }



}
