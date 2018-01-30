package com.ntg.user.mvpsample.data.sourse.remote;

import com.ntg.user.mvpsample.data.Task;
import com.ntg.user.mvpsample.data.sourse.TasksDataSource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by islam on 1/30/2018.
 */

public class AddTaskRepo implements TasksDataSource.SaveTask {

    private static AddTaskRepo INSTANCE = null;


    public static AddTaskRepo getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AddTaskRepo();
        }
        return INSTANCE;
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
