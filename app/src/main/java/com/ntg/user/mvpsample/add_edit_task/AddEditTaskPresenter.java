package com.ntg.user.mvpsample.add_edit_task;

import com.ntg.user.mvpsample.data.Task;
import com.ntg.user.mvpsample.data.source.TasksDataSource;

/**
 * AddEditTaskPresenter control adding task operation and refreshing tasks list with new task
 */

public class AddEditTaskPresenter extends AddEditTaskContract.Presenter {

    private AddEditTaskContract.View addTaskView;
    private TasksDataSource tasksRepository;

    public AddEditTaskPresenter(AddEditTaskContract.View addTaskView, TasksDataSource tasksRepository) {
        this.addTaskView = addTaskView;
        this.tasksRepository = tasksRepository;
        super.setFragment(addTaskView);
        addTaskView.setPresenter(this);
        tasksRepository.setPresenter(this);
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

    @Override
    public void editTask(Task task) {
        tasksRepository.upDateTask(task, new TasksDataSource.SaveTaskCallBack() {
            @Override
            public void onTaskSaved() {
                addTaskView.showSaveTaskSuccessMsg();
            }

            @Override
            public void onTaskFailed(String errorMsg) {
                addTaskView.showSaveTaskFailedMsg();
            }
        });
        addTaskView.showTaskDetail(task);
    }
}
