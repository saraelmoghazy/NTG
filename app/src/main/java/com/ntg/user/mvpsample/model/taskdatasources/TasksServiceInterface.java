package com.ntg.user.mvpsample.model.taskdatasources;

import com.ntg.user.mvpsample.model.Subtask;
import com.ntg.user.mvpsample.model.Task;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * TasksServiceInterface define requests call that will send to server
 */

public interface TasksServiceInterface {

    /**
     * getTask get response contains list of all tasks that have been added
     */
    @GET("tasks")
    Observable<List<Task>> getTasks();

    @GET("tasks/{taskId}/subtasks")
    Observable<List<Subtask>> getSubTasks(@Path("taskId") String taskId);


    @POST("tasks")
    Call<Task> addTask(@Body Task task);}

