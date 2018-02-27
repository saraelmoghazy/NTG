package com.ntg.user.mvpsample.add_story.view;

import com.ntg.user.mvpsample.base.BaseView;

/**
 * @author Sara Elmoghazy
 */
public interface AddStoryViewContract extends BaseView {


    void navigateToAddTasksFragments(int storyId);

    void showTitleMissingError();

    void showDescriptionMissingError();
}
