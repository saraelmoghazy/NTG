package com.ntg.user.mvpsample.data.source.remote.network;

import com.ntg.user.mvpsample.data.Subtask;
import com.ntg.user.mvpsample.data.Task;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
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
    Observable<List<Task>> getTasks();

    @GET("tasks/{taskId}/subtasks")
    Observable<List<Subtask>> getSubTasks(@Path("taskId") String taskId);

    @GET("tasks/{taskId}/subtasks/{title}")
    Observable<Subtask> getSubTask(@Path("taskId") String taskId, @Path("title") String title);

    /**
     * navigateToAddTaskUI add task to tasks on server
     *
     * @param task the task to be added
     */
    @POST("tasks")
    Observable<Task> addTask(@Body Task task);

    /**
     * editTask used to update task attribute
     *
     * @param taskId to specify the required task to be updated
     * @param task   the task instance with updates
     */
    @PUT("tasks/updateTask/{taskId}")
    Observable<Task> editTask(@Path("taskId") String taskId, @Body Task task);

    @DELETE("tasks/deleteTask/{taskId}")
    Observable<Task> deleteTask(@Path("taskId") String taskId);
}
