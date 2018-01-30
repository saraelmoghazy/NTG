package com.ntg.user.mvpsample.data.source.remote.network;

import com.ntg.user.mvpsample.data.Task;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * TasksServiceInterface define requests call that will send to server
 */

public interface TasksServiceInterface {

    /**
     * getTask get response contains list of all tasks that have been added
     */
    @GET("tasks")
    Call<List<Task>> getTasks();

    /**
     * navigateToAddTaskUI add task to tasks on server
     *
     * @param task the task to be added
     */
    @POST("tasks")
    Call<Task> addTask(@Body Task task);

    /**
     * editTask used to update task attribute
     *
     * @param taskId to specify the required task to be updated
     * @param task   the task instance with updates
     */
    @PUT("tasks/{taskId}")
    Call<Task> editTask(@Path("taskId") String taskId, @Body Task task);
}
