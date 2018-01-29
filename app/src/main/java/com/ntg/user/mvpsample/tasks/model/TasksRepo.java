package com.ntg.user.mvpsample.tasks.model;

import com.ntg.user.mvpsample.tasks.model.local.LocalTasksRepo;
import com.ntg.user.mvpsample.tasks.model.remote.RemoteTasksRepo;

/**
 * Created by devsaad on 1/29/2018.
 */

public class TasksRepo implements TasksDataSource {

    private RemoteTasksRepo remoteTasksRepo;
    private LocalTasksRepo localTasksRepo;

    public TasksRepo() {
        remoteTasksRepo = new RemoteTasksRepo();
        localTasksRepo = new LocalTasksRepo();
    }

    @Override
    public void getTasks(GetTasksCallBack getTasksCallBack) {
        remoteTasksRepo.getTasks(getTasksCallBack);
    }
}
