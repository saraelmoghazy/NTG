package com.ntg.user.mvpsample.data;

import java.util.List;

/**
 * Created by devsaad on 1/29/2018.
 */

public interface TaskDataSource {
    interface GetTaskCallBack{
        void onTaskLoaded(List<Task> taskList);
        void onTaskFailed(String errMsg);
    }
    void loadData(GetTaskCallBack getTaskCallBack);

}
