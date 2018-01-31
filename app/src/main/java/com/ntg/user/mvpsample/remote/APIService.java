package com.ntg.user.mvpsample.remote;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by GM7 on 1/29/2018.
 */

public interface APIService {
    
    @GET("tasks")
    Observable<List<Task>> getPosts();
    
    @POST("addTask")
    Call<Task> saveTask(@Body Task task);
    
    
}

