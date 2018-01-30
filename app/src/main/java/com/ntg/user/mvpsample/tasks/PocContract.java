package com.ntg.user.mvpsample.tasks;

import com.ntg.user.mvpsample.BasePresenter;
import com.ntg.user.mvpsample.tasks.data.Poc;

import java.util.List;

/**
 * Created by mohamed yassin on 1/29/2018.
 */

public class PocContract {
    public interface IPocView extends BaseView<PocPresenter> {

        void showPocs(List<Poc> pocList);

        void showNoPocs();

        void showMessageError(String msgError);

        void showLoadingIndicator(boolean isShown);
    }


    public interface IPocPresenter extends BasePresenter {
        void getPocs(boolean isRefresh);
    }
}
