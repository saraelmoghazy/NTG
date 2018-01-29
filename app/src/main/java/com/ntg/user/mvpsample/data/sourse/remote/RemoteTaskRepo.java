package com.ntg.user.mvpsample.data.sourse.remote;

import android.service.autofill.SaveCallback;
import android.util.Log;

import com.ntg.user.mvpsample.data.Task;
import com.ntg.user.mvpsample.data.sourse.TasksDataSource;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by islam on 1/28/2018.
 */

public class RemoteTaskRepo implements TasksDataSource {
    private static RemoteTaskRepo instance;

    private RemoteTaskRepo(){
    }

    public static RemoteTaskRepo getInstance() {
        if (instance == null)
            instance = new RemoteTaskRepo();
        return instance;
    }


    @Override
    public void getTasks(final LoadTasksCallback loadTasksCallback) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Task>> call = apiService.getTasks();
        call.enqueue(new Callback<List<Task>>() {
            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {

                if (response.isSuccessful()){
                    List<Task> tasks = new ArrayList<>();
                    tasks.addAll(response.body());
                    Log.e("task" , tasks.get(0).getTitle());
                    loadTasksCallback.onTasksLoaded(tasks);
                }
            }

            @Override
            public void onFailure(Call<List<Task>> call, Throwable t) {
                loadTasksCallback.onTaskLoadedFail(t.getMessage());
            }
        });

    }

    @Override
    public void saveTask(SaveCallback saveCallback) {
            }
}
