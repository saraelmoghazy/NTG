package com.ntg.user.mvpsample.add_tasks;

import com.ntg.user.mvpsample.data.Task;
import com.ntg.user.mvpsample.data.sourse.TasksDataSource;
import com.ntg.user.mvpsample.data.sourse.TasksRepository;

import java.util.UUID;

/**
 * Created by islam on 1/29/2018.
 */

public class AddTaskPresenter implements IAddTaskPresenter {

    private final IAddTaskView addTaskView;
    private final TasksRepository repository;

    public AddTaskPresenter(IAddTaskView addTaskView, TasksRepository repository) {
        this.addTaskView = addTaskView;
        this.repository = repository;
    }

    @Override
    public void start() {
    }


    @Override
    public void saveTask(Task task) {
        repository.saveTask(task);
    }
}
