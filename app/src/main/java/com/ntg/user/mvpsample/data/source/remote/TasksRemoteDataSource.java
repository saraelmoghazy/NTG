package com.ntg.user.mvpsample.data.source.remote;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import com.ntg.user.mvpsample.data.source.remote.network.RetrofitExceptionConverter;
import com.ntg.user.mvpsample.utils.MathUtil;
import com.ntg.user.mvpsample.data.Subtask;
import com.ntg.user.mvpsample.data.Task;
import com.ntg.user.mvpsample.data.source.TasksDataSource;
import com.ntg.user.mvpsample.data.source.remote.network.TasksAPI;
import com.ntg.user.mvpsample.data.source.remote.network.TasksServiceInterface;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * TasksRemoteDataSource contains the logic of sending requests to server and getting data
 * from responses
 */

public class TasksRemoteDataSource implements TasksDataSource {

    @SuppressLint("StaticFieldLeak")
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
        serviceInterface.getTasks()
                .flatMap(this::convert)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tasksCallBack::onTasksLoaded,
                        t -> {
                            String message = RetrofitExceptionConverter
                                    .convertToRetrofitException(t).getMessage();
                            tasksCallBack.onTasksFailed(message);
                        });
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
        Call<Void> call = serviceInterface.addTask(task);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                saveTaskCallBack.onTaskSaved();
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
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
    public void upDateTask(Task task, SaveTaskCallBack saveTaskCallBack) {
        TasksServiceInterface serviceInterface =
                TasksAPI.getClient().create(TasksServiceInterface.class);
        Call<Void> call = serviceInterface.editTask(task.getId(), task);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                saveTaskCallBack.onTaskSaved();
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                saveTaskCallBack.onTaskFailed(t.getMessage());
            }
        });
    }

    @Override
    public void deleteTask(Task task) {
        TasksServiceInterface serviceInterface =
                TasksAPI.getClient().create(TasksServiceInterface.class);
        serviceInterface.deleteTask(task.getId()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {

            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                call.clone().enqueue(this);
            }
        });
    }

    @Override
    public boolean getTaskProgress(String taskId) {
        final boolean[] isOver90 = {false};
        TasksServiceInterface serviceInterface =
                TasksAPI.getClient().create(TasksServiceInterface.class);

        serviceInterface.getSubTasks(taskId)
                .flatMapIterable(subtasks -> subtasks)
                .map(Subtask::getProgress)
                .toList()
                .subscribe(list -> isOver90[0] = MathUtil.average(list) > 90);
        return isOver90[0];
    }
}
