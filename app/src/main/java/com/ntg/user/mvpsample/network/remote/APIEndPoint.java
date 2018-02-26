package com.ntg.user.mvpsample.network.remote;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * @author Sara Elmoghazy
 */
public interface APIEndPoint {


    @GET("tasks")
    Observable<List<Task>> getTasks();

    @POST("saveTask")
    Observable<Task> saveTask(@Body Task task);

    @PUT("saveSubTask/{taskId}")
    Observable<SubTask> saveSubTask(@Path("taskId") int id, @Body SubTasks subTasks);
}
