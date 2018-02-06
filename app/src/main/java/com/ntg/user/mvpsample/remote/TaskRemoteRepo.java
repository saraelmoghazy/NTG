package com.ntg.user.mvpsample.remote;

import android.util.Log;

import com.ntg.user.mvpsample.TasksDataSource;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;


/**
 * Created by GM7 on 1/29/2018.
 */

public class TaskRemoteRepo implements TasksDataSource {
    
    public static TaskRemoteRepo newInstance() {
        return new TaskRemoteRepo();
    }
    
    
    @Override
    public void getPosts(final TasksDataSource.GetPostsCallBack getPostsCallBack) {
        APIService mAPIService = ApiClient.getClient().create(APIService.class);
        mAPIService.getPosts().
                observeOn(AndroidSchedulers.mainThread()).
                subscribeOn(Schedulers.io()).
                subscribe(new Observer<List<Task>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }
                    
                    @Override
                    public void onNext(@NonNull List<Task> tasks) {
                        getPostsCallBack.onPostsLoaded(tasks);
                        
                        
                    }
                    
                    @Override
                    public void onError(@NonNull Throwable e) {
                        getPostsCallBack.onPostsFailed(e.getMessage());
                        if (e instanceof HttpException) {
                            // We had non-2XX http error
                        }
                        if (e instanceof IOException) {
                            // A network or conversion error happened
                        }
                        
                    }
                    
                    @Override
                    public void onComplete() {
                    }
                });
    }
    
    @Override
    public void saveTasks(final Task task, final SavePostsCallBack savePostsCallBack) {
        APIService mAPIService = ApiClient.getClient().create(APIService.class);
        mAPIService.saveTask(task).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Task>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    
                    @Override
                    public void onNext(Task task) {
                        savePostsCallBack.onPostsSaved(task);
                    }
                    
                    @Override
                    public void onError(Throwable e) {
                        savePostsCallBack.onPostsFailed(e.getMessage());
                    }
                    
                    @Override
                    public void onComplete() {
                    }
                });
        
    }
    
    @Override
    public void updateTasks(Task task, UpdatePostsCallBack updatePostsCallBack) {
        APIService mAPIService = ApiClient.getClient().create(APIService.class);
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
    
    @Override
    public void saveSubTask(String taskId, SubTask subTask, SaveSubTaskCallBack saveSubTaskCallBack) {
        APIService service = ApiClient.getClient().create(APIService.class);
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
}

