package com.ntg.user.mvpsample.tasks;

import com.ntg.user.mvpsample.data.Task;
import com.ntg.user.mvpsample.data.source.TasksDataSource;

import java.util.List;

/**
 * TasksPresenter control getting data from TasksRepository and pass it to view to show
 */

public class TasksPresenter extends TasksContract.Presenter {

    private TasksContract.View tasksView;
    private TasksDataSource tasksRepository;

    public TasksPresenter(TasksContract.View tasksView, TasksDataSource tasksRepository) {
        this.tasksView = tasksView;
        this.tasksRepository = tasksRepository;
        super.setFragment(tasksView);
        tasksView.setPresenter(this);
        tasksRepository.setPresenter(this);
    }

    @Override
    public void start() {
        getTasks();
    }

    /**
     * getTasks ask TasksRepository to load data from server and pass the returned tasks list
     * to the view to show
     */
    @Override
    public void getTasks() {
        tasksView.showLoadingIndicator();
        tasksRepository.loadData(tasks -> {
            if (tasks != null && tasks.size() != 0) {
                tasksView.showTasks(tasks);
            } else {
                tasksView.showNoTasks();
            }
        });
    }

    /**
     * navigateToAddTaskUI ask view to navigate to addTask activity
     */
    @Override
    public void navigateToAddTaskUI() {
        tasksView.showAddNewTaskUI();
    }

    @Override
    public void deleteTask(Task task) {
        tasksRepository.deleteTask(task);
        getTasks();
    }
}
