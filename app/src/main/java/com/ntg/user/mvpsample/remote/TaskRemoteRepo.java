package com.ntg.user.mvpsample.remote;

import android.util.Log;

import com.ntg.user.mvpsample.DaggerNetComponent;
import com.ntg.user.mvpsample.RetrofitProvider;
import com.ntg.user.mvpsample.TasksDataSource;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by GM7 on 1/29/2018.
 */

public class TaskRemoteRepo implements TasksDataSource {
    public static final String TAG = TaskRemoteRepo.class.getSimpleName();

    private static TaskRemoteRepo instance;
    @Inject
    RetrofitProvider retrofit;

    public static TaskRemoteRepo newInstance() {
        instance = new TaskRemoteRepo();
        DaggerNetComponent.Initializer.buildComponent().inject(instance);
        return instance;
    }


    @Override
    public void getPosts(final TasksDataSource.GetPostsCallBack getPostsCallBack) {
        APIService mAPIService = retrofit.getRetrofit().create(APIService.class);
        mAPIService.getPosts().
                observeOn(AndroidSchedulers.mainThread()).
                subscribeOn(Schedulers.io()).
                subscribe(tasks -> getPostsCallBack.onPostsLoaded(tasks),
                        throwable -> {
                            if (throwable instanceof RetrofitException) {
                                RetrofitException retrofitException = (RetrofitException) throwable;
                                switch (((RetrofitException) throwable).getErrorType()) {
                                    case ErrorType.HTTP:
                                        getPostsCallBack.onPostsFailed(throwable.getMessage());
                                    case ErrorType.NETWORK:
                                        getPostsCallBack.onPostsFailed("Server Error: check your connection");
                                    default:
                                        getPostsCallBack.onPostsFailed(throwable.getMessage());
                                }
                            }
                        });
    }

    @Override
    public void saveTasks(final Task task, final SavePostsCallBack savePostsCallBack) {
        APIService mAPIService = retrofit.getRetrofit().create(APIService.class);
        mAPIService.saveTask(task).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(v -> savePostsCallBack.onPostsSaved(task),
                        throwable -> {
                            if (throwable instanceof RetrofitException) {
                                RetrofitException retrofitException = (RetrofitException) throwable;
                                switch (((RetrofitException) throwable).getErrorType()) {
                                    case ErrorType.HTTP:
                                        savePostsCallBack.onPostsFailed(throwable.getMessage());
                                    case ErrorType.NETWORK:
                                        savePostsCallBack.onPostsFailed("Server Error: check your connection");
                                    default:
                                        savePostsCallBack.onPostsFailed(throwable.getMessage());
                                }
                            }
                        });

    }


    @Override
    public void saveSubTask(String taskId, SubTask subTask, SaveSubTaskCallBack saveSubTaskCallBack) {
        APIService service = retrofit.getRetrofit().create(APIService.class);
        service.saveSubTask(taskId, subTask).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(v -> saveSubTaskCallBack.onSubTaskSaved(),
                        throwable -> {
                            if (throwable instanceof RetrofitException) {
                                RetrofitException retrofitException = (RetrofitException) throwable;
                                switch (retrofitException.getErrorType()) {
                                    case ErrorType.HTTP:
                                        saveSubTaskCallBack.onSubTaskFailed(retrofitException.getMessage());
                                    case ErrorType.NETWORK:
                                        saveSubTaskCallBack.onSubTaskFailed(retrofitException.getMessage());
                                }
                            }
                        });
    }

    //!!!!!!>>>>>>Not used !!//
    @Override
    public void updateTasks(Task task, UpdatePostsCallBack updatePostsCallBack) {

        APIService mAPIService = retrofit.getRetrofit().create(APIService.class);
        mAPIService.updatePost("", task).
                observeOn(AndroidSchedulers.mainThread()).
                subscribeOn(Schedulers.io()).
                subscribe(new Observer<Task>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Task task) {
                        //savePostsCallBack.onPostsSaved(response.body());
                        updatePostsCallBack.onPostsUpdated(task);

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }
}

