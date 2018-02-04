package com.ntg.user.mvpsample.taskdetail;

import com.ntg.user.mvpsample.BaseView;
import com.ntg.user.mvpsample.data.SubTask;
import com.ntg.user.mvpsample.data.Task;

import java.util.List;

/**
 * Created by islam on 1/30/2018.
 */

public interface ITaskDetailsView extends BaseView {
    void showTitle(String title);
    void showDescription(String description);
    void showSubTasks(List<SubTask> subTasks);
    void navigateToSubTasksFragment();
}
