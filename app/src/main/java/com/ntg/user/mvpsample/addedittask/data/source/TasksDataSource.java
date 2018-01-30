package com.ntg.user.mvpsample.addedittask.data.source;

import android.support.annotation.NonNull;

import com.ntg.user.mvpsample.Task;

import java.util.List;

/**
 * Created by mohamed yassin on 1/28/2018.
 */

public interface TasksDataSource {
    void saveTask(@NonNull Task task);
}
