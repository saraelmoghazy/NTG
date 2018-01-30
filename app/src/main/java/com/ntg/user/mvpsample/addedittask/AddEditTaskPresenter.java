package com.ntg.user.mvpsample.addedittask;

import android.support.annotation.NonNull;

import com.ntg.user.mvpsample.Task;
import com.ntg.user.mvpsample.addedittask.data.source.TasksDataSource;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * Created by mohamed yassin on 1/28/2018.
 */

public class AddEditTaskPresenter implements AddEditTaskContract.Presenter {
    @NonNull
    private final TasksDataSource mTasksRepository;
    @NonNull
    private final AddEditTaskContract.View mAddTaskView;


    // Creates a presenter for the add/edit view.

    public AddEditTaskPresenter(@NonNull TasksDataSource tasksRepository,
                                @NonNull AddEditTaskContract.View addTaskView) {

        mTasksRepository = checkNotNull(tasksRepository);
        mAddTaskView = checkNotNull(addTaskView);
        mAddTaskView.setPresenter(this);
    }

    @Override
    public void start() {
    }

    @Override
    public void saveTask(String title, String description) {
        Task newTask = new Task(title, description);
        if (newTask.getTitle().isEmpty()) {
            mAddTaskView.showEmptyTaskError();
        } else {
            mTasksRepository.saveTask(newTask);
            mAddTaskView.showTasksList();
        }

    }
}
