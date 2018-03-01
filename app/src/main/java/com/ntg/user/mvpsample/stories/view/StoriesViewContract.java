package com.ntg.user.mvpsample.stories.view;

import android.view.View;

import com.ntg.user.mvpsample.base.BaseView;
import com.ntg.user.mvpsample.model.Story;

import java.util.List;

/**
 * @author Sara Elmoghazy
 */
public interface StoriesViewContract extends BaseView {

    void showStories(List<Story> stories);

    void addNewStory();

    void navigateToStorySummary(Story story);

    void navigateToUpdateTasks(Story story);
}
