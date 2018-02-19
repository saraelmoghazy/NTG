package com.ntg.user.mvpsample.add_task.presenter;

import com.ntg.user.mvpsample.add_task.AddTaskViewContract;
import com.ntg.user.mvpsample.add_task.model.AddTaskComponent;
import com.ntg.user.mvpsample.add_task.model.AddTaskRepository;
import com.ntg.user.mvpsample.base.BaseFetchObserver;
import com.ntg.user.mvpsample.network.Task;

import javax.inject.Inject;

/**
 * @author islam
 */

public class AddTaskPresenter implements AddTaskPresenterContract {

    @Inject
    AddTaskRepository repository;
    private final AddTaskViewContract addTaskViewContract;

    public AddTaskPresenter(AddTaskViewContract addTaskViewContract) {
        AddTaskComponent.Initializer.buildComponent().inject(this);
        this.addTaskViewContract = addTaskViewContract;
    }

    @Override
    public void start() {}

    @Override
    public void saveTask(Task task) {
        BaseFetchObserver<Task> observer = new BaseFetchObserver<Task>() {
            @Override
            public void onNext(Task tasks) {
                addTaskViewContract.showAddTaskSuccess("Task Added");
            }
        };
        repository.saveTask(task).subscribe(observer);
    }
}
