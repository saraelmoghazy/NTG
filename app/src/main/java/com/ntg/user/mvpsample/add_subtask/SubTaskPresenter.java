package com.ntg.user.mvpsample.add_subtask;

import com.ntg.user.mvpsample.data.SubTask;
import com.ntg.user.mvpsample.data.sourse.TasksDataSource;

import javax.inject.Inject;

/**
 * @author islam
 */

public class SubTaskPresenter implements ISubTaskPresenter {

    @Inject
    AddSubTaskRepo addSubTaskRepo;
    private final ISubTaskView iSubTaskView;

    public SubTaskPresenter(ISubTaskView iSubTaskView) {
        this.iSubTaskView = iSubTaskView;
        DaggerAddSubTaskComponent.Initializer.buildComponent().inject(this);
    }

    @Override
    public void start() {
    }

    @Override
    public void saveSubTask(String id, SubTask subTasks) {
        addSubTaskRepo.saveSubTask(id, subTasks, new TasksDataSource.SaveSubTask.AddSubTaskCallback() {
            @Override
            public void onSubTaskAdded(SubTask subTask) {
                iSubTaskView.showSuccess("Success");
            }

            @Override
            public void inSubTaskAddedFail(String errMessage) {
                iSubTaskView.showFail("Fail To Add SubTask");
            }
        });
    }
}
