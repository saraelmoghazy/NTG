package com.ntg.user.mvpsample.base;

/**
 * Created by ilias on 25/01/2018.
 */

public interface IPresenter extends ErrorCallback {
    void start();
    void onError(String errorMsg);
}
