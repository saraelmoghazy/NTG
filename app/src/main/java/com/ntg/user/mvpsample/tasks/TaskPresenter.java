package com.ntg.user.mvpsample.tasks;


import com.ntg.user.mvpsample.data.Task;
import com.ntg.user.mvpsample.data.source.TasksDataSource;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class TaskPresenter implements TaskContract.Presenter {

    private TaskContract.View tasksView;
    private TasksDataSource tasksRepository;

    public TaskPresenter(TaskContract.View tasksView, TasksDataSource tasksRepository) {
        this.tasksView = tasksView;
        this.tasksRepository = tasksRepository;
        tasksView.setPresenter(this);
    }


    @Override
    public void start() {
        getTasks();
    }

    @Override
    public void getTasks() {
        tasksRepository.loadTask(new TasksDataSource.GetTaskCallback() {
            @Override
            public void onTasksLoaded(List<Task> tasks) {
                if (tasks != null && tasks.size() != 0) {
                    tasksView.showTasks(tasks);
                } else {
                    tasksView.showNoTasks();
                }
            }

            @Override
            public void onDataNotAvailable(String msgError) {
                tasksView.showMessageError();

            }
        });

    }



}
