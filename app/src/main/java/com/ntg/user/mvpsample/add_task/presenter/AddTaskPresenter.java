package com.ntg.user.mvpsample.add_task.presenter;

import com.ntg.user.mvpsample.add_task.IAddTaskView;
import com.ntg.user.mvpsample.add_task.model.AddTaskComponent;
import com.ntg.user.mvpsample.add_task.model.AddTaskRepository;
import com.ntg.user.mvpsample.base.BaseFetchObserver;
import com.ntg.user.mvpsample.network.Task;

import javax.inject.Inject;

/**
 * @author islam
 */

public class AddTaskPresenter implements IAddTaskPresenter {

    @Inject
    AddTaskRepository repository;
    private final IAddTaskView iAddTaskView;

    public AddTaskPresenter(IAddTaskView iAddTaskView) {
        AddTaskComponent.Initializer.buildComponent().inject(this);
        this.iAddTaskView = iAddTaskView;
    }

    @Override
    public void start() {}

    @Override
    public void saveTask(Task task) {
        BaseFetchObserver<Task> observer = new BaseFetchObserver<Task>() {
            @Override
            public void onNext(Task tasks) {
                iAddTaskView.showAddTaskSuccess("Task Added");
            }
        };
        repository.saveTask(task).subscribe(observer);
    }
}
