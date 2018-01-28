package com.ntg.user.mvpsample.data.source.remote;

import android.content.Context;

import com.ntg.user.mvpsample.data.Task;
import com.ntg.user.mvpsample.data.source.TasksDataSource;
import com.ntg.user.mvpsample.data.source.remote.network.TasksAPI;
import com.ntg.user.mvpsample.data.source.remote.network.TasksServiceInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ilias on 25/01/2018.
 */

public class TasksRemoteDataSource implements TasksDataSource {
    //    static TasksRemoteDataSource INSTANCE;
    private Context context;

    public TasksRemoteDataSource(Context context) {
        this.context = context;
    }

//    public static TasksDataSource getINSTANCE() {
//        if (INSTANCE == null){
//            INSTANCE = new TasksRemoteDataSource();
//        }
//        return INSTANCE;
//    }

    @Override
    public void loadRemoteData(final GetTasksCallBack tasksCallBack) {
        TasksServiceInterface serviceInterface =
                TasksAPI.getClient(context).create(TasksServiceInterface.class);
        Call<List<Task>> call = serviceInterface.getTasks();
        call.enqueue(new Callback<List<Task>>() {
            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
                tasksCallBack.onTasksLoaded(response.body());
            }

            @Override
            public void onFailure(Call<List<Task>> call, Throwable t) {
                tasksCallBack.onTasksFailed(t.getMessage());
                call.clone().enqueue(this);
            }
        });
    }

    @Override
    public Task saveTask(Task task) {
        TasksServiceInterface serviceInterface =
                TasksAPI.getClient(context).create(TasksServiceInterface.class);
        final Task[] result = new Task[1];
        Call<Task> call = serviceInterface.addTask(task);
        call.enqueue(new Callback<Task>() {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {
                result[0] = response.body();
            }

            @Override
            public void onFailure(Call<Task> call, Throwable t) {
                call.clone().enqueue(this);
            }
        });
        return result[0];
    }
}
