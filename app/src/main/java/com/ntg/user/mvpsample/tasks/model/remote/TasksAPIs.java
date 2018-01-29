package com.ntg.user.mvpsample.tasks.model.remote;

import com.ntg.user.mvpsample.data.Task;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by devsaad on 1/28/2018.
 */

public interface TasksAPIs {

    @GET("tasks")
    Call<List<Task>> getTask();
}

