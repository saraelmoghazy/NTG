package com.ntg.user.mvpsample.add_task.presenter;

import com.ntg.user.mvpsample.add_task.AddTaskViewContract;
import com.ntg.user.mvpsample.add_task.model.AddTaskComponent;
import com.ntg.user.mvpsample.add_task.model.AddTaskRepository;
import com.ntg.user.mvpsample.base.BaseFetchObserver;
import com.ntg.user.mvpsample.base.BasePresenter;

import javax.inject.Inject;

/**
 * @author Sara Elmoghazy
 */

public class AddTaskPresenter extends BasePresenter<AddTaskViewContract> {

    @Inject
    AddTaskRepository repository;

    public AddTaskPresenter(AddTaskViewContract addTaskViewContract) {
        super(addTaskViewContract);
        AddTaskComponent.Initializer.buildComponent().inject(this);
    }

    private void saveTask(Task task) {
        showLoadingIndicator();
        BaseFetchObserver<Task> observer = new BaseFetchObserver<Task>(this) {
            @Override
            public void onNext(Task task) {
                hideLoadingIndicator();
                getView().showAddTaskSuccess(task.getTitle());
                getView().navigateToTasksFragments();
            }
        };
        repository.saveTask(task).subscribe(observer);
    }

    public void onTaskClicked(String title, String description) {
        if (title.isEmpty()) {
            getView().showTitleMissedError();
        } else if (description.isEmpty()) {
            getView().showDescriptionMissedError();
        } else {
            saveTask(new Task(title, description));
        }
    }
}
