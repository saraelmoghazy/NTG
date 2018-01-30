package com.ntg.user.mvpsample.data;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by devsaad on 1/29/2018.
 */

public class TaskRepository implements TaskDataSource{

    @Override
    public void loadData(final GetTaskCallBack getTaskCallBack) {
        TaskServiceInterface taskServiceInterface = ServiceGenerator.getClient()
                .create(TaskServiceInterface.class);
        Call<List<Task>> call = taskServiceInterface.getTask();
        call.enqueue(new Callback<List<Task>>() {
            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
                getTaskCallBack.onTaskLoaded(response.body());
            }

            @Override
            public void onFailure(Call<List<Task>> call, Throwable t) {
                getTaskCallBack.onTaskFailed(t.getMessage());
            }
        });
    }

}
