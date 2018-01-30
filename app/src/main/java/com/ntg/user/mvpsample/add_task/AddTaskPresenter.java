package com.ntg.user.mvpsample.add_task;

import com.ntg.user.mvpsample.data.Task;
import com.ntg.user.mvpsample.data.source.TasksDataSource;

/**
 * AddTaskPresenter control adding task operation and refreshing tasks list with new task
 */

public class AddTaskPresenter implements AddTaskContract.Presenter {

    private AddTaskContract.View addTaskView;
    private TasksDataSource tasksRepository;

    AddTaskPresenter(AddTaskContract.View addTaskView, TasksDataSource tasksRepository) {
        this.addTaskView = addTaskView;
        this.tasksRepository = tasksRepository;
        addTaskView.setPresenter(this);
    }

    @Override
    public void start() {
    }

    /**
     * saveTask ask TasksRepository to add new task to server
     *
     * @param task the task that will be added
     */
    @Override
    public void saveTask(Task task) {
        tasksRepository.saveTask(task, new TasksDataSource.SaveTaskCallBack() {
            @Override
            public void onTaskSaved() {
                addTaskView.showSaveTaskSuccessMsg();
            }

            @Override
            public void onTaskFailed(String errorMsg) {
                addTaskView.showSaveTaskFailedMsg();
            }
        });
        addTaskView.showTasks();
    }
}
