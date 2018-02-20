package com.ntg.user.mvpsample.add_subtask.presenter;

import com.ntg.user.mvpsample.add_subtask.model.AddSubTaskRepository;
import com.ntg.user.mvpsample.add_subtask.model.DaggerAddSubTaskComponent;
import com.ntg.user.mvpsample.add_subtask.view.SubTaskViewContract;
import com.ntg.user.mvpsample.base.BaseFetchObserver;
import com.ntg.user.mvpsample.base.BasePresenter;
import com.ntg.user.mvpsample.network.SubTask;

import java.util.UUID;

import javax.inject.Inject;

/**
 * @author Sara Elmoghazy
 */

public class SubTaskPresenter extends BasePresenter<SubTaskViewContract> {

    @Inject
    AddSubTaskRepository addSubTaskRepository;

    public SubTaskPresenter(SubTaskViewContract view) {
        super(view);
        DaggerAddSubTaskComponent.Initializer.buildComponent().inject(this);
    }

    private void saveSubTask(int id, SubTask subTask) {
        showLoadingIndicator();
        BaseFetchObserver<SubTask> observer = new BaseFetchObserver<SubTask>(this) {
            @Override
            public void onNext(SubTask subTask1) {
                hideLoadingIndicator();
                getView().showAddSubTaskSuccess(subTask1.getTitle());
            }
        };
        addSubTaskRepository.saveSubTask(id, subTask).subscribe(observer);
    }

    public void onAddSubTaskClicked(String title, String description, int progress, int taskId) {
        if (title.isEmpty()) {
            getView().showTitleMissedError();
        } else if (description.isEmpty()) {
            getView().showDescriptionMissedError();
        } else {
            SubTask subTask = new SubTask(UUID.randomUUID().toString()
                    , title
                    , description
                    , progress);
            saveSubTask(taskId, subTask);
        }
    }
}
