package com.ntg.user.mvpsample.remote;

import android.util.Log;


import com.ntg.user.mvpsample.TasksDataSource;

import java.util.List;

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
        mAPIService.getPosts().enqueue(new Callback<List<Task>>() {
            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
                if (response.isSuccessful()) {
                    List<Task> tasks = response.body();
                    getPostsCallBack.onPostsLoaded(tasks);
                }
            }

            @Override
            public void onFailure(Call<List<Task>> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
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

