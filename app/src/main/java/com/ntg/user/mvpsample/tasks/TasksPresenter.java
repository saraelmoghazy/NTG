package com.ntg.user.mvpsample.tasks;

import com.ntg.user.mvpsample.data.Task;
import com.ntg.user.mvpsample.data.source.TasksDataSource;

import java.util.List;

/**
 * TasksPresenter control getting data from TasksRepository and pass it to view to show
 */

public class TasksPresenter implements TasksContract.Presenter {

    private TasksContract.View tasksView;
    private TasksDataSource tasksRepository;

    public TasksPresenter(TasksContract.View tasksView, TasksDataSource tasksRepository) {
        this.tasksView = tasksView;
        this.tasksRepository = tasksRepository;
        tasksView.setPresenter(this);
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
        tasksRepository.loadData(new TasksDataSource.GetTasksCallBack() {
            @Override
            public void onTasksLoaded(List<Task> tasks) {
                if (tasks != null && tasks.size() != 0) {
                    tasksView.showTasks(tasks);
                } else {
                    tasksView.showNoTasks();
                }
            }

            @Override
            public void onTasksFailed(String errorMsg) {
                tasksView.showNetworkError();
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

    /**
     * updateTaskStatus ask TasksRepository to update task status
     */
    @Override
    public void updateTaskStatus(Task task) {
        tasksRepository.upDateTask(task);
    }

    @Override
    public void updateTask(Task task) {

    }

    @Override
    public void deleteTask(Task task) {
        tasksRepository.deleteTask(task);
        getTasks();
    }
}
