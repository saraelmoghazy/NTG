package com.ntg.user.mvpsample.data.source;

import com.ntg.user.mvpsample.data.Task;

/**
 * Created by ilias on 25/01/2018.
 */

public interface TasksDataSource {
void loadRemoteData();
Task saveTask(Task task);
}
