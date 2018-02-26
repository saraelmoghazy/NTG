package com.ntg.user.mvpsample.add_subtask.presenter;

import com.ntg.user.mvpsample.add_subtask.model.AddSubTaskRepository;
import com.ntg.user.mvpsample.add_subtask.model.DaggerAddSubTaskComponent;
import com.ntg.user.mvpsample.add_subtask.view.SubTaskViewContract;
import com.ntg.user.mvpsample.base.BaseFetchObserver;
import com.ntg.user.mvpsample.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;
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
        SubTasks subTasksRequest = new SubTasks();
        List<SubTask> subTasks = new ArrayList<>();
        subTasks.add(subTask);
        subTasksRequest.setSubTasks(subTasks);
        addSubTaskRepository.saveSubTask(id, subTasksRequest).subscribe(observer);
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
