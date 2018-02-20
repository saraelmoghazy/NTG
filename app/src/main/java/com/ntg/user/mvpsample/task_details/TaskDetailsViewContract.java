package com.ntg.user.mvpsample.task_details;

import com.ntg.user.mvpsample.base.BaseView;
import com.ntg.user.mvpsample.network.SubTask;

import java.util.List;

/**
 * @author Sara Elmoghazy
 */

public interface TaskDetailsViewContract extends BaseView {

    void showTitle(String title);

    void showDescription(String description);

    void showSubTasks(List<SubTask> subTasks);

    void navigateToSubTasksFragment();
}
