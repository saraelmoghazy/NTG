package com.ntg.user.mvpsample.data.source.remote;

import android.util.Log;

import com.ntg.user.mvpsample.base.BasePresenter;
import com.ntg.user.mvpsample.data.source.remote.network.ErrorObserver;
import com.ntg.user.mvpsample.data.source.remote.network.NetworkComponent;
import com.ntg.user.mvpsample.utils.MathUtil;
import com.ntg.user.mvpsample.data.Subtask;
import com.ntg.user.mvpsample.data.Task;
import com.ntg.user.mvpsample.data.source.TasksDataSource;
import com.ntg.user.mvpsample.data.source.remote.network.TasksServiceInterface;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * TasksRemoteDataSource contains the logic of sending requests to server and getting data
 * from responses
 */

public class TasksRemoteDataSource implements TasksDataSource {

    private static TasksRemoteDataSource INSTANCE;
    private BasePresenter basePresenter;
    Retrofit retrofit;

    private TasksRemoteDataSource(Retrofit retrofit) {
        this.retrofit = retrofit;
        }

    public static TasksRemoteDataSource getInstance(Retrofit retrofit) {
        if (INSTANCE == null) {
            INSTANCE = new TasksRemoteDataSource(retrofit);
        }

        return INSTANCE;
    }

    @Override
    public void setPresenter(BasePresenter basePresenter) {
        this.basePresenter = basePresenter;
    }

    /**
     * loadData create GET request to get all saved tasks
     *
     * @param tasksCallBack to carry result of loading data to the user of this method
     */
    @Override
    public void loadData(final GetTasksCallBack tasksCallBack) {
        ErrorObserver<List<Task>> errorObserver = new ErrorObserver<List<Task>>(basePresenter) {
            @Override
            public void onNext(List<Task> tasks) {
                tasksCallBack.onTasksLoaded(tasks);
            }
        };
        TasksServiceInterface serviceInterface =
                retrofit.create(TasksServiceInterface.class);
        serviceInterface.getTasks()
                .subscribeOn(Schedulers.io())
                .flatMap(this::changeStatusUponProgressAverage)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(errorObserver);
    }

    private Observable<List<Task>> changeStatusUponProgressAverage(List<Task> tasks) {
        for (Task task : tasks)
            if (task.getSubtasks() != null && !task.getSubtasks().isEmpty()) {
                task.setCompleted(getTaskProgress(task));
            } else {
                task.setCompleted(false);
            }

        return Observable.just(tasks);
    }

    private boolean getTaskProgress(Task task) {
        List<Subtask> subtasks = task.getSubtasks();
        List<Integer> progressList = new ArrayList<>(subtasks.size());
        for (Subtask subtask : subtasks) {
            progressList.add(subtask.getProgress());
        }
        return MathUtil.average(progressList) > 90;
    }

    /**
     * saveTask create POST request to add new task to server
     *
     * @param task             the task that will be added
     * @param saveTaskCallBack to carry saving task result
     */
    @Override
    public void saveTask(Task task, SaveTaskCallBack saveTaskCallBack) {
        ErrorObserver<Task> errorObserver = new ErrorObserver<Task>(basePresenter) {
            @Override
            public void onNext(Task task) {
                saveTaskCallBack.onTaskSaved();
            }
        };
        TasksServiceInterface serviceInterface =
                retrofit.create(TasksServiceInterface.class);
        serviceInterface.addTask(task).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(errorObserver);
    }

    /**
     * updateTask create PUT request to update specific task
     *
     * @param task task that will be updated
     */
    @Override
    public void upDateTask(Task task, SaveTaskCallBack saveTaskCallBack) {
        ErrorObserver<Task> errorObserver = new ErrorObserver<Task>(basePresenter) {
            @Override
            public void onNext(Task task) {
                saveTaskCallBack.onTaskSaved();
            }
        };
        TasksServiceInterface serviceInterface =
                retrofit.create(TasksServiceInterface.class);
        serviceInterface.editTask(task.getId(), task)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(errorObserver);
    }

    @Override
    public void deleteTask(Task task) {
        ErrorObserver<Void> errorObserver = new ErrorObserver<Void>(basePresenter) {
            @Override
            public void onNext(Void task) {
                super.onNext(task);
            }
        };
        TasksServiceInterface serviceInterface =
                retrofit.create(TasksServiceInterface.class);
        serviceInterface.deleteTask(task.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(errorObserver);
    }
}
