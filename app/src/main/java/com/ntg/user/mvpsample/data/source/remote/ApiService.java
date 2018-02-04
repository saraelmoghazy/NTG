package com.ntg.user.mvpsample.data.source.remote;

import com.ntg.user.mvpsample.data.Subtask;
import com.ntg.user.mvpsample.data.Task;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

 @POST("tasks")
 Observable<Task> saveTask(@Body Task task);
 @GET("tasks")
 Observable<List<Task>> getTasks();
 @GET("tasks/{TaskId}/subtasks")
 Observable<List<Subtask>> getSubTasks(@Path("TaskId") String taskId);
}
