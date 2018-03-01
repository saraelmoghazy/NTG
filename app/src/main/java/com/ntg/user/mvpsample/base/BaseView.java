package com.ntg.user.mvpsample.base;

/**
 * @author Sara Elmoghazy
 */
public interface BaseView {

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void showErrorMessage(String message);

    void showNoInternetConnectionMessage();
}
