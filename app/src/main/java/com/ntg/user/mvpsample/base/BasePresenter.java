package com.ntg.user.mvpsample.base;

/**
 * Created by ilias on 07/02/2018.
 */

public abstract class BasePresenter implements IPresenter {

    BaseFragment fragment;

    public void setFragment(BaseFragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void onError(String errorMsg) {
        fragment.showErrorMsg(errorMsg);
    }
}
