package com.ntg.user.mvpsample.data.sourse.remote;

import com.ntg.user.mvpsample.data.Task;
import com.ntg.user.mvpsample.data.sourse.TasksDataSource;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RemoteTaskRepo implements TasksDataSource {

    private static RemoteTaskRepo instance;

    private RemoteTaskRepo(){
    }

    public static RemoteTaskRepo getInstance() {
        if (instance == null)
            instance = new RemoteTaskRepo();
        return instance;
    }


    @Override
    public void getTasks(final LoadTasksCallback loadTasksCallback) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Task>> call = apiService.getTasks();
        call.enqueue(new Callback<List<Task>>() {
            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {

                if (response.isSuccessful()){
                    List<Task> tasks = new ArrayList<>();
                    tasks.addAll(response.body());
                    loadTasksCallback.onTasksLoaded(tasks);
                }
            }

            @Override
            public void onFailure(Call<List<Task>> call, Throwable t) {
                loadTasksCallback.onTaskLoadedFail(t.getMessage());
            }
        });

    }

    @Override
    public Task saveTask(Task task) {
        ApiInterface addNewTask = ApiClient.getClient().create(ApiInterface.class);
        Call call = addNewTask.saveTask(task);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {

            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });

        return task;
    }
}
