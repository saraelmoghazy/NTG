package com.ntg.user.mvpsample.data;

import com.ntg.user.mvpsample.data.Task;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * This interface contains function getTask that will get tasks from the sever .
 */

public interface TaskServiceInterface {

    @GET("tasks")
    retrofit2.Call<List<Task>> getTask();
}

