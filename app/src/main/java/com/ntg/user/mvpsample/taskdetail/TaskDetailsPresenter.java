package com.ntg.user.mvpsample.taskdetail;

import android.util.Log;

import com.ntg.user.mvpsample.data.Task;
import com.ntg.user.mvpsample.task.ITaskPresenter;

/**
 * @author islam
 */

public class TaskDetailsPresenter implements ITaskDetailsPresenter{

    private ITaskDetailsView iTaskDetailsView;
    private Task task;

    public TaskDetailsPresenter(ITaskDetailsView iTaskDetailsView, Task task) {
        this.iTaskDetailsView = iTaskDetailsView;
        this.task = task;
    }

    @Override
    public void start(){
        Log.e("task" , task.getTitle());
        iTaskDetailsView.showTitle(task.getTitle());
        iTaskDetailsView.showDescription(task.getDescription());
        iTaskDetailsView.showState(task.getCompleted());
    }
}
