package com.ntg.user.mvpsample.add_tasks;

import com.ntg.user.mvpsample.BaseView;

/**
 * @author islam
 */

public interface IAddTaskView extends BaseView {
    void showAddSuccess(String s);
    void showAddFail(String s);
}
