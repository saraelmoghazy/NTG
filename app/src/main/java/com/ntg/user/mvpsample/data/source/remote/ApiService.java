package com.ntg.user.mvpsample.data.source.remote;




import com.ntg.user.mvpsample.data.Task;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

 @POST("tasks")
 Call<Task> saveTask(@Body Task task);
 @GET("tasks")
 Call<List<Task>> getTasks();
}
