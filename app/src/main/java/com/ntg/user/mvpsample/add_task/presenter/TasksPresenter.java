package com.ntg.user.mvpsample.add_task.presenter;

import com.ntg.user.mvpsample.add_task.model.AddTaskRepository;
import com.ntg.user.mvpsample.add_task.model.DaggerAddTaskComponent;
import com.ntg.user.mvpsample.add_task.view.TasksViewContract;
import com.ntg.user.mvpsample.base.BaseFetchObserver;
import com.ntg.user.mvpsample.base.BasePresenter;
import com.ntg.user.mvpsample.model.StoryTask;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import retrofit2.Response;

/**
 * Task Presenter which handle tasks screen events
 * Help to add /Edit task locally then after submit all tasks it commits them to backend.
 *
 * @author Sara Elmoghazy
 */
public class TasksPresenter extends BasePresenter<TasksViewContract> {

    @Inject
    AddTaskRepository addTaskRepository;
    List<StoryTask> storyTasks = new ArrayList<>();

    public TasksPresenter(TasksViewContract view) {
        super(view);

        DaggerAddTaskComponent.Initializer.buildComponent().inject(this);
    }

    /**
     * triggered when screen started with story tasks (Update tasks mode)
     *
     * @param storyTasks
     */
    public void onStoryTasksExists(List<StoryTask> storyTasks) {
        this.storyTasks = storyTasks;
        getView().updateTaskList(storyTasks);
    }

    /**
     * triggered when user click on task, it show edit task dialog
     *
     * @param storyTask
     */
    public void onTaskClicked(StoryTask storyTask) {
        getView().showTaskDialog(storyTask);
    }

    /**
     * triggered when user click add task, it show add task dialog
     */
    public void onAddTaskClicked() {
        getView().showTaskDialog(new StoryTask(UUID.randomUUID().toString(), 0));
    }

    /**
     * triggered when user click ok button in add task dialog
     * check whether is new task or modified one then add/edit according to.
     *
     * @param storyTask
     */
    public void onOkDialogClicked(StoryTask storyTask) {
        if (!isTaskValid(storyTask.getTitle())) {
            getView().dismissTaskDialog();
            boolean isTaskExists = false;
            for (StoryTask task : storyTasks) {
                if (task.getId().equals(storyTask.getId())) {
                    editTask(storyTask);
                    isTaskExists = true;
                    break;
                }
            }
            if (!isTaskExists)
                addTask(storyTask);
        } else {
            getView().showTaskNotValid();
        }
    }

    /**
     * triggered  when user click cancel button in add task dialog
     */
    public void onCancelDialogClicked() {
        getView().dismissTaskDialog();
    }

    /**
     * validate if task has title
     *
     * @param title
     */
    private boolean isTaskValid(String title) {
        return (title == null || title.isEmpty());
    }

    /**
     * Add new task
     *
     * @param storyTask
     */
    private void addTask(StoryTask storyTask) {
        storyTasks.add(storyTask);
        getView().updateTaskList(storyTasks);
    }

    /**
     * Edit task
     *
     * @param storyTask
     */
    private void editTask(StoryTask storyTask) {
        storyTask.setTitle(storyTask.getTitle());
        storyTask.setProgress(storyTask.getProgress());
        getView().updateTaskList(storyTasks);
    }

    /**
     * Triggered when user click Done button , tasks would not be submitted to backend before Done.
     *
     * @param storyId
     */
    public void onSubmitTasks(int storyId) {
        showLoadingIndicator();
        BaseFetchObserver<Response<Void>> observer = new BaseFetchObserver<Response<Void>>(this) {
            @Override
            public void onNext(Response<Void> aVoid) {
                hideLoadingIndicator();
                getView().navigateToStories();
            }
        };
        addTaskRepository.saveTasks(storyId, storyTasks).subscribe(observer);
    }

}
