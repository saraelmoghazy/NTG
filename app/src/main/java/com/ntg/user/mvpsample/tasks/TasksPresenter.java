package com.ntg.user.mvpsample.tasks;

import com.ntg.user.mvpsample.data.Task;
import com.ntg.user.mvpsample.data.TaskDataSource;
import com.ntg.user.mvpsample.data.TaskRepository;

import java.util.List;

/**
 * Tasks presenter is the head of the app.
 * It contains instances of view and model to achive the purpos of MVP.
 * It is the only Decition maker in the project.
 */

public class TasksPresenter implements TasksContract.Presenter {
    MainActivity view;
    TaskRepository taskRepository;


    public TasksPresenter(MainActivity view, TaskRepository taskRepository) {
        this.view = view;
        this.taskRepository = taskRepository;
    }

    /**
     * load tasks
     **/
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
