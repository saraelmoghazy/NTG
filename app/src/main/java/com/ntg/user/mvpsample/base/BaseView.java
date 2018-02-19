package com.ntg.user.mvpsample.base;

/**
 * Created by Sara Elmoghazy on 19/02/2018.
 */

public interface BaseView {

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void showErrorMessage(String message);
}
