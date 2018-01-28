package com.ntg.user.mvpsample.data.source.remote.network;

import com.ntg.user.mvpsample.data.Task;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ilias on 25/01/2018.
 */

public interface TasksServiceInterface {

    @GET("tasks/{taskId}")
    Call<List<Task>> getTasks(@Path("taskId") String taskId);

    @GET("tasks")
    Call<List<Task>> getTasks();

    @POST("tasks")
    Call<Task> addTask(@Body Task task);

    @POST("tasks/{taskId}")
    Call<Task> editTask(@Path("taskId")String taskId, @Body Task task);
}
