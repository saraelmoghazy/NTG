package com.ntg.user.mvpsample.model.taskdatasources;

import android.support.annotation.NonNull;
import android.util.Log;

import com.ntg.user.mvpsample.tasks.CalculatAverage;
import com.ntg.user.mvpsample.model.Subtask;
import com.ntg.user.mvpsample.model.Task;
import com.ntg.user.mvpsample.model.taskdatasources.TasksDataSource;
import com.ntg.user.mvpsample.model.taskdatasources.TasksAPI;
import com.ntg.user.mvpsample.model.taskdatasources.TasksServiceInterface;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * TasksRemoteDataSource contains the logic of sending requests to server and getting data
 * from responses
 */

public class TasksRemoteDataSource implements TasksDataSource {

    private static TasksRemoteDataSource INSTANCE;

    @Inject
    Retrofit retrofit;

    private TasksRemoteDataSource() {
    }

    public static TasksRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TasksRemoteDataSource();
            DaggerNetComponent.Intializer.buildComponent().inject(INSTANCE);
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
        serviceInterface.getTasks()
                .flatMap(this::convert)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tasksCallBack::onTasksLoaded,
                        t -> tasksCallBack.onTasksFailed(t.getMessage()));
    }

    private Observable<List<Task>> convert(List<Task> tasks) {
        for (Task task : tasks)
            if (task.getSubtasks() != null && !task.getSubtasks().isEmpty()) {
                task.setCompleted(getTaskProgress(task.getId()));
            } else {
                task.setCompleted(false);
            }

        return Observable.just(tasks);
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
            public void onResponse(Call<Task> call, Response<Task> response) {

            }

            @Override
            public void onFailure(Call<Task> call, Throwable t) {

            }
        });
}

    @Override
    public boolean getTaskProgress(String taskId) {
        final boolean[] isOver90 = {false};
        TasksServiceInterface tasksServiceInterface = TasksAPI.getClient().create(TasksServiceInterface.class);
        tasksServiceInterface.getSubTasks(taskId)
                .flatMapIterable(subtasks -> subtasks)
                .map(Subtask::getProgress)
                .toList()
                .subscribe(list -> isOver90[0] = CalculatAverage.average(list) > 90);
        return isOver90[0];
    }
}

