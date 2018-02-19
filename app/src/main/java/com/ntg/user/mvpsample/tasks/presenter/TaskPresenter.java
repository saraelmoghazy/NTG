package com.ntg.user.mvpsample.tasks.presenter;

import com.ntg.user.mvpsample.base.BaseFetchObserver;
import com.ntg.user.mvpsample.base.BasePresenter;
import com.ntg.user.mvpsample.network.Task;
import com.ntg.user.mvpsample.tasks.model.GetTasksComponent;
import com.ntg.user.mvpsample.tasks.model.GetTasksRepository;
import com.ntg.user.mvpsample.tasks.view.TaskViewContract;

import java.util.List;

import javax.inject.Inject;

/**
 * @author islam class task presenter
 */

public class TaskPresenter extends BasePresenter<TaskViewContract> implements TaskPresenterContract {

    @Inject
    GetTasksRepository tasksRepository;

    public TaskPresenter(TaskViewContract view) {
        super(view);
        GetTasksComponent.Initializer.buildComponent().inject(this);
    }

    @Override
    public void loadTasks() {
        getView().showLoadingIndicator();
        BaseFetchObserver<List<Task>> observer = new BaseFetchObserver<List<Task>>(this) {
            @Override
            public void onNext(List<Task> tasks) {
                getView().showTasks(tasks);
                getView().hideLoadingIndicator();
            }
        };
        tasksRepository.getTasks().subscribe(observer);
    }

    @Override
    public void start() {
        super.start();
        loadTasks();
    }
}
