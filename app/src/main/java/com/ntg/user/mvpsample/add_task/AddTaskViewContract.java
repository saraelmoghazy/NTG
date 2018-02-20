package com.ntg.user.mvpsample.add_task;

import com.ntg.user.mvpsample.base.BaseView;

/**
 * @author Sara Elmoghazy
 */
public interface AddTaskViewContract extends BaseView {

    void showAddTaskSuccess(String taskTitle);

    void navigateToTasksFragments();

    void showTitleMissedError();

    void showDescriptionMissedError();
}
