package com.ntg.user.mvpsample.add_subtask.presenter;

import com.ntg.user.mvpsample.add_subtask.SubTaskViewContract;
import com.ntg.user.mvpsample.add_subtask.model.AddSubTaskRepository;
import com.ntg.user.mvpsample.add_subtask.model.DaggerAddSubTaskComponent;
import com.ntg.user.mvpsample.network.SubTask;

import javax.inject.Inject;

/**
 * @author islam
 */

public class SubTaskPresenter implements SubTaskPresenterContract {
    @Inject
    AddSubTaskRepository addSubTaskRepository;
    private final SubTaskViewContract subTaskViewContract;

    public SubTaskPresenter(SubTaskViewContract subTaskViewContract) {
        this.subTaskViewContract = subTaskViewContract;
        DaggerAddSubTaskComponent.Initializer.buildComponent().inject(this);
    }

    @Override
    public void start() {
    }

    @Override
    public void saveSubTask(String id, SubTask subTask) {
        addSubTaskRepository.saveSubTask(id, subTask)
                .subscribe(savedSubTask -> subTaskViewContract.showSuccess(savedSubTask.getTitle()));
    }
}
