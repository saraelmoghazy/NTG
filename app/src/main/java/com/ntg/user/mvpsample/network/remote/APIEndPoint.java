package com.ntg.user.mvpsample.network.remote;

import com.ntg.user.mvpsample.network.SubTask;
import com.ntg.user.mvpsample.network.Task;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
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

    @PUT("saveSubTask/{id}")
    Observable<SubTask> saveSubTask(@Path("id") int id, @Body SubTask subTasks);

    @GET("tasks/{id}/subTasks")
    Observable<List<SubTask>> getSubTasks(@Path("id") int id);
}
