package com.ntg.user.mvpsample.add_task.view;

import com.ntg.user.mvpsample.base.BaseView;
import com.ntg.user.mvpsample.model.StoryTask;

import java.util.List;

/**
 * @author Sara Elmoghazy
 */

public interface TasksViewContract extends BaseView {

    void updateTaskList(List<StoryTask> storyTasks);

    void navigateToStories();

    void showTaskNotValid();

    void showTaskDialog(StoryTask storyTask);

    void dismissTaskDialog();

}
