package com.ntg.user.mvpsample.tasks;

import com.ntg.user.mvpsample.data.Task;
import com.ntg.user.mvpsample.data.TaskDataSource;
import com.ntg.user.mvpsample.data.TaskRepository;

import java.util.List;

/**
 * Created by devsaad on 1/30/2018.
 */

public class TasksPresenter implements TasksContract.Presenter {
    MainActivity view;
    TaskRepository taskRepository;


    public TasksPresenter(MainActivity view, TaskRepository taskRepository) {
        this.view = view;
        this.taskRepository = taskRepository;
    }

    public void start() {
        taskRepository.loadData(new TaskDataSource.GetTaskCallBack() {
            @Override
            public void onTaskLoaded(List<Task> taskList) {
                view.showTask(taskList);
            }

            @Override
            public void onTaskFailed(String errMsg) {

            }
        });
    }

}
