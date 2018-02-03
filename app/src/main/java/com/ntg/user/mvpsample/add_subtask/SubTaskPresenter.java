package com.ntg.user.mvpsample.add_subtask;

import com.ntg.user.mvpsample.data.SubTask;
import com.ntg.user.mvpsample.data.sourse.remote.AddSubTaskRepo;

/**
 * Created by islam on 2/1/2018.
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
        addSubTaskRepo.saveSubTask(id , subTasks);
    }
}
