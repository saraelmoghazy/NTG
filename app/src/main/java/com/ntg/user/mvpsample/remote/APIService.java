package com.ntg.user.mvpsample.remote;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by GM7 on 1/29/2018.
 */

public interface APIService {
    
    @GET("tasks")
    Observable<List<Task>> getPosts();
    
    @POST("addTask")
    Observable<Task> saveTask(@Body Task task);
    
    @PUT("/updateTask/{id}")
    @FormUrlEncoded
    Observable<Task> updatePost(@Path("id") String taskId,@Body Task task);
    
    
}

