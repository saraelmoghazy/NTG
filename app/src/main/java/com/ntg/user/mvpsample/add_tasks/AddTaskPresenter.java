package com.ntg.user.mvpsample.add_tasks;

import com.ntg.user.mvpsample.data.Task;
import com.ntg.user.mvpsample.data.sourse.remote.AddTaskRepo;

/**
 * @author islam
 */

public class AddTaskPresenter implements IAddTaskPresenter{

    private final AddTaskRepo repository;

    public AddTaskPresenter(AddTaskRepo repository) {
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
