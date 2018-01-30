package com.ntg.user.mvpsample.data.sourse.remote;

import com.ntg.user.mvpsample.data.Task;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by islam on 1/28/2018.
 */

public interface ApiInterface {

    @GET("tasks")
    Call<List<Task>> getTasks();

    @POST("tasks")
    Call<Task> saveTask(@Body Task task);
}
