package com.ntg.user.mvpsample.data;

import com.ntg.user.mvpsample.data.Task;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by devsaad on 1/28/2018.
 */

public interface TaskServiceInterface {

    @GET("tasks")
    retrofit2.Call<List<Task>> getTask();
}

