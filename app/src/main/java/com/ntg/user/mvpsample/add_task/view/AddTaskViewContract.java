package com.ntg.user.mvpsample.add_task.view;

import com.ntg.user.mvpsample.base.BaseView;
import com.ntg.user.mvpsample.model.StoryTask;

import java.util.List;

/**
 * @author Sara Elmoghazy
 */

public interface AddTaskViewContract extends BaseView {

    void updateTasks(StoryTask storyTask);

    void navigateToStoriesActivity();

}
