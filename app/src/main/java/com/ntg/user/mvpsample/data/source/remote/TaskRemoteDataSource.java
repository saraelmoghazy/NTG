package com.ntg.user.mvpsample.data.source.remote;



import android.support.annotation.NonNull;

import com.ntg.user.mvpsample.data.Task;
import com.ntg.user.mvpsample.data.source.TasksDataSource;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



/**
 * Created by mohamed yassin on 2/2/2018.
 */

public class TaskRemoteDataSource implements TasksDataSource {
    private static TaskRemoteDataSource INSTANCE;

    private TaskRemoteDataSource() {
    }

    public static TaskRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TaskRemoteDataSource();
        }

        return INSTANCE;
    }
    @Override
    public void loadTask(final GetTaskCallback tasksCallBack) {
        ApiService apiService =
                ApiClient.getClient().create(ApiService.class);

        Call<List<Task>> call = apiService.getTasks();
        call.enqueue(new Callback<List<Task>>() {
            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {

                    tasksCallBack.onTasksLoaded(response.body());


            }

            @Override
            public void onFailure(Call<List<Task>> call, Throwable t) {
                tasksCallBack.onDataNotAvailable(t.getMessage());
                call.clone().enqueue(this);

            }
        });

    }

    @Override
    public void saveTask(Task task, final SaveTaskCallBack saveTaskCallBack) {
        ApiService apiService =
                ApiClient.getClient().create(ApiService.class);
        Call<Task> call = apiService.saveTask(task);
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


}
