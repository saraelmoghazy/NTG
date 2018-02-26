package com.ntg.user.mvpsample.tasks.presenter;

import com.ntg.user.mvpsample.base.BaseFetchObserver;
import com.ntg.user.mvpsample.base.BasePresenter;
import com.ntg.user.mvpsample.tasks.model.GetTasksComponent;
import com.ntg.user.mvpsample.tasks.model.GetTasksRepository;
import com.ntg.user.mvpsample.tasks.view.TaskViewContract;

import java.util.List;

import javax.inject.Inject;

/**
 * @author Sara Elmoghazy
 */

public class TaskPresenter extends BasePresenter<TaskViewContract> {

    @Inject
    GetTasksRepository tasksRepository;

    public TaskPresenter(TaskViewContract view) {
        super(view);
        GetTasksComponent.Initializer.buildComponent().inject(this);
    }


    private void loadTasks() {
        showLoadingIndicator();
        BaseFetchObserver<List<Task>> observer = new BaseFetchObserver<List<Task>>(this) {
            @Override
            public void onNext(List<Task> tasks) {
                hideLoadingIndicator();
                getView().showTasks(tasks);
            }
        };
        tasksRepository.getTasks().subscribe(observer);
    }

    @Override
    public void start() {
        super.start();
        loadTasks();
    }

    public void onTaskClicked(Task task) {
        getView().navigateToTaskDetails(task);
    }
}
