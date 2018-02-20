package com.ntg.user.mvpsample.add_subtask.view;

import com.ntg.user.mvpsample.base.BaseView;

/**
 * @author Sara Elmoghazy
 */

public interface SubTaskViewContract extends BaseView {

    void showAddSubTaskSuccess(String title);

    void showTitleMissedError();

    void showDescriptionMissedError();
}
