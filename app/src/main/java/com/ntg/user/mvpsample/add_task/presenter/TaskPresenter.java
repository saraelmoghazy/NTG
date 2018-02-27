package com.ntg.user.mvpsample.add_task.presenter;

import com.ntg.user.mvpsample.add_task.model.AddTaskRepository;
import com.ntg.user.mvpsample.add_task.model.DaggerAddTaskComponent;
import com.ntg.user.mvpsample.add_task.view.AddTaskViewContract;
import com.ntg.user.mvpsample.base.BaseFetchObserver;
import com.ntg.user.mvpsample.base.BasePresenter;
import com.ntg.user.mvpsample.model.StoryTask;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Response;

/**
 * @author Sara Elmoghazy
 */

public class TaskPresenter extends BasePresenter<AddTaskViewContract> {

    @Inject
    AddTaskRepository addTaskRepository;

    public TaskPresenter(AddTaskViewContract view) {
        super(view);

        DaggerAddTaskComponent.Initializer.buildComponent().inject(this);
    }


    public void onAddTask(String title, int progress) {
        StoryTask storyTask = new StoryTask(title, progress);
        getView().updateTasks(storyTask);
    }

    public void onSubmitTasks(int taskId, List<StoryTask> storyTasks) {
        showLoadingIndicator();
        BaseFetchObserver<Response<Void>> observer = new BaseFetchObserver<Response<Void>>(this) {
            @Override
            public void onNext(Response<Void> aVoid) {
                hideLoadingIndicator();
                getView().navigateToStoriesActivity();
            }
        };
        addTaskRepository.saveTasks(taskId, storyTasks).subscribe(observer);
    }
}
