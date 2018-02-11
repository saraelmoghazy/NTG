package com.ntg.user.mvpsample.task;

import com.ntg.user.mvpsample.data.Task;
import com.ntg.user.mvpsample.data.sourse.TasksDataSource;
import com.ntg.user.mvpsample.data.sourse.TasksRepository;

import java.util.List;

import javax.inject.Inject;


/**
 * @author islam class task presenter
 */

public class TaskPresenter implements ITaskPresenter {

    public static final String TAG = TaskPresenter.class.getSimpleName();
    @Inject
    TasksRepository tasksRepository;
    private final ITaskView iTaskView;
    TaskComponent taskComponent;

    public TaskPresenter(ITaskView iTaskView) {
        this.iTaskView = iTaskView;
        getTaskComponent().inject(this);
    }

    public TaskComponent getTaskComponent() {
        taskComponent = DaggerTaskComponent.Initializer.buildComponent();
        return taskComponent;
    }

    @Override
    public void loadTasks() {
        iTaskView.showLoadingIndicator(true);
        tasksRepository.getTasks(new TasksDataSource.LoadTasksCallback() {
            @Override
            public void onTasksLoaded(List<Task> tasks) {
                iTaskView.showTasks(tasks);
                iTaskView.showLoadingIndicator(false);
            }

            @Override
            public void onTaskLoadedFail(String errMesg) {
                iTaskView.showErrorMessage(errMesg);
            }
        });
    }

    @Override
    public void start() {
        loadTasks();
    }
}
