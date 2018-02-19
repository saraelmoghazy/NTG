package com.ntg.user.mvpsample.add_subtask.presenter;

import com.ntg.user.mvpsample.add_subtask.ISubTaskView;
import com.ntg.user.mvpsample.add_subtask.model.AddSubTaskRepository;
import com.ntg.user.mvpsample.add_subtask.model.DaggerAddSubTaskComponent;
import com.ntg.user.mvpsample.network.SubTask;

import javax.inject.Inject;

/**
 * @author islam
 */

public class SubTaskPresenter implements ISubTaskPresenter {
    @Inject
    AddSubTaskRepository addSubTaskRepository;
    private final ISubTaskView iSubTaskView;

    public SubTaskPresenter(ISubTaskView iSubTaskView) {
        this.iSubTaskView = iSubTaskView;
        DaggerAddSubTaskComponent.Initializer.buildComponent().inject(this);
    }

    @Override
    public void start() {
    }

    @Override
    public void saveSubTask(String id, SubTask subTask) {
        addSubTaskRepository.saveSubTask(id, subTask)
                .subscribe(savedSubTask -> iSubTaskView.showSuccess(savedSubTask.getTitle()));
    }
}
