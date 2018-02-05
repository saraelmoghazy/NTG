package com.ntg.user.mvpsample.add_subtask;

import com.ntg.user.mvpsample.data.SubTask;
import com.ntg.user.mvpsample.data.sourse.TasksDataSource;
import com.ntg.user.mvpsample.data.sourse.remote.AddSubTaskRepo;

/**
 * @author islam
 */

public class SubTaskPresenter implements ISubTaskPresenter {

    private final AddSubTaskRepo addSubTaskRepo;
    private final ISubTaskView iSubTaskView;

    public SubTaskPresenter(AddSubTaskRepo addSubTaskRepo, ISubTaskView iSubTaskView) {
        this.addSubTaskRepo = addSubTaskRepo;
        this.iSubTaskView = iSubTaskView;
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
