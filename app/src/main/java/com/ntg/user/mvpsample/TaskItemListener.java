package com.ntg.user.mvpsample;

import com.ntg.user.mvpsample.data.Task;

/**
 * @author islam listner to help to send object when task clicked from recyclerview
 */

public interface TaskItemListener {
    void onTaskClicked(Task task);
}
