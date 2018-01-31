package com.ntg.user.mvpsample.remote;

import android.util.Log;


import com.ntg.user.mvpsample.TasksDataSource;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

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
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void saveTasks(final Task task, final SavePostsCallBack savePostsCallBack) {

        APIService mAPIService = ApiClient.getClient().create(APIService.class);
        Call<Task> call = mAPIService.saveTask(task);
        call.enqueue(new Callback<Task>() {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {
                savePostsCallBack.onPostsSaved(response.body());
            }

            @Override
            public void onFailure(Call<Task> call, Throwable t) {
            }
        });
    }

}

