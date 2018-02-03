package com.ntg.user.mvpsample.data.sourse.remote;

import com.ntg.user.mvpsample.data.SubTask;
import com.ntg.user.mvpsample.data.Task;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by islam on 1/28/2018.
 */

public interface ApiInterface {


    @GET("tasks")
    @Headers("Content-type: application/json")
    Observable<List<Task>> getTasks();

    @POST("tasks")
    Observable<Task> saveTask(@Body Task task);

    @PUT("tasks/{id}")
    Observable<List<SubTask>> saveSubTask(@Path("id")String id , @Body SubTask subTasks);

    @GET("tasks/{id}/subTasks")
    Observable<List<SubTask>> getSubTasks(@Path("id") String id);
}
