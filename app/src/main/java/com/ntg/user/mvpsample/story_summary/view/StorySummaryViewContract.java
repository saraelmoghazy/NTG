package com.ntg.user.mvpsample.story_summary.view;

import com.ntg.user.mvpsample.base.BaseView;

/**
 * @author Sara Elmoghazy
 */

public interface StorySummaryViewContract extends BaseView {

    void showIcon(String title);

    void showTitle(String title);

    void showDescription(String description);

    void showNewTasks(int notStartedCount);

    void showStoryProgress(int progress);

    void showDoneTasks(int doneSubTasks);

    void showInProgressTasks(int inProgressSubTasks);

}
