package com.ntg.user.mvpsample.story_summary.view;

import com.ntg.user.mvpsample.base.BaseView;

/**
 * @author Sara Elmoghazy
 */

public interface StorySummaryViewContract extends BaseView {

    void showTitle(String title);

    void showDescription(String description);

    void showNotStartedSubTasks(int notStartedCount);

    void showProgress(int progress);

    void showDoneSubTasks(int doneSubTasks);

    void showInProgressSubTasks(int inProgressSubTasks);

}
