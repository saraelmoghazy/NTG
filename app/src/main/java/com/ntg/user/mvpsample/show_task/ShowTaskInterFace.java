package com.ntg.user.mvpsample.show_task;

import com.ntg.user.mvpsample.PresenterLayer.InterfacePresenter;
import com.ntg.user.mvpsample.PresenterLayer.ViewInterface;

/**
 * ShowTaskInterFace
 */

public interface ShowTaskInterFace {

    interface View extends ViewInterface<Presenter> {
        void showTaskDetails();
    }

    interface Presenter extends InterfacePresenter {void start();}
}
