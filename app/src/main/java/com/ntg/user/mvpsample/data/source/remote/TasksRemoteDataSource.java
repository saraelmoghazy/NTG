package com.ntg.user.mvpsample.data.source.remote;

import android.content.Context;
import android.support.annotation.NonNull;

import com.ntg.user.mvpsample.data.Task;
import com.ntg.user.mvpsample.data.source.TasksDataSource;
import com.ntg.user.mvpsample.data.source.remote.network.TasksAPI;
import com.ntg.user.mvpsample.data.source.remote.network.TasksServiceInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * TasksRemoteDataSource contains the logic of sending requests to server and getting data
 * from responses
 */

public class TasksRemoteDataSource implements TasksDataSource {

    private static TasksRemoteDataSource INSTANCE;

    private TasksRemoteDataSource() {
    }

    public static TasksRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TasksRemoteDataSource();
        }

        return INSTANCE;
    }

    /**
     * loadData create GET request to get all saved tasks
     *
     * @param tasksCallBack to carry result of loading data to the user of this method
     */
    @Override
    public void loadData(final GetTasksCallBack tasksCallBack) {
        TasksServiceInterface serviceInterface =
                TasksAPI.getClient().create(TasksServiceInterface.class);
        Call<List<Task>> call = serviceInterface.getTasks();
        call.enqueue(new Callback<List<Task>>() {
            @Override
            public void onResponse(@NonNull Call<List<Task>> call, @NonNull Response<List<Task>> response) {
                tasksCallBack.onTasksLoaded(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Task>> call, @NonNull Throwable t) {
                tasksCallBack.onTasksFailed(t.getMessage());
                call.clone().enqueue(this);
            }
        });
    }

    /**
     * saveTask create POST request to add new task to server
     *
     * @param task             the task that will be added
     * @param saveTaskCallBack to carry saving task result
     */
    @Override
    public void saveTask(Task task, SaveTaskCallBack saveTaskCallBack) {
        TasksServiceInterface serviceInterface =
                TasksAPI.getClient().create(TasksServiceInterface.class);
        Call<Task> call = serviceInterface.addTask(task);
        call.enqueue(new Callback<Task>() {
            @Override
            public void onResponse(@NonNull Call<Task> call, @NonNull Response<Task> response) {
                saveTaskCallBack.onTaskSaved();
            }

            @Override
            public void onFailure(@NonNull Call<Task> call, @NonNull Throwable t) {
                saveTaskCallBack.onTaskFailed(t.getMessage());
            }
        });
    }

    /**
     * updateTask create PUT request to update specific task
     *
     * @param task task that will be updated
     */
    @Override
    public void upDateTask(Task task) {
        TasksServiceInterface serviceInterface =
                TasksAPI.getClient().create(TasksServiceInterface.class);
        Call<Task> call = serviceInterface.editTask(task.getId(), task);
        call.enqueue(new Callback<Task>() {
            @Override
            public void onResponse(@NonNull Call<Task> call, @NonNull Response<Task> response) {
            }

            @Override
            public void onFailure(@NonNull Call<Task> call, @NonNull Throwable t) {
                call.clone().enqueue(this);
            }
        });
    }
}
