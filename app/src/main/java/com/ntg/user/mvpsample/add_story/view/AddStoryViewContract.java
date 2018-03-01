package com.ntg.user.mvpsample.add_story.view;

import com.ntg.user.mvpsample.base.BaseView;
import com.ntg.user.mvpsample.model.Story;

/**
 * @author Sara Elmoghazy
 */
public interface AddStoryViewContract extends BaseView {


    void navigateToAddTasks(Story story);

    void showTitleMissingError();

    void showDescriptionMissingError();
}
