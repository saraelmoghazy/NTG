package com.ntg.user.mvpsample.base;

/**
 * Created by ilias on 25/01/2018.
 */

public interface BaseView<T> {
    void setPresenter(T presenter);
    void showErrorMsg(String errorMsg);
}
